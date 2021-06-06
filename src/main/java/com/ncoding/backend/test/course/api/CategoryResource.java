package com.ncoding.backend.test.course.api;

import static java.util.Optional.ofNullable;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncoding.backend.test.course.core.Category;
import com.ncoding.backend.test.course.service.CategoryService;
import com.ncoding.backend.test.course.util.AElog;
import com.ncoding.backend.test.course.util.AEutil;
import com.ncoding.backend.test.course.util.exception.response.custom.CustomRuntimeException;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "categories", description = "the category API")
public class CategoryResource {

    private final Logger logger = LoggerFactory.getLogger(CategoryResource.class);

    @Autowired
    private AEutil util;

    @Autowired
    private CategoryService service;

    @Operation(summary = "Get all categories")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "found categories", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Category.class))) }),
            @ApiResponse(responseCode = "404", description = "No Categories found", content = @Content) })
    @GetMapping
    public ResponseEntity<Object> findAllObjects(@Nullable Integer status, @Nullable String name,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "page", defaultValue = "0") Integer page, HttpServletRequest request) {

        Integer totalRecords;
        List<Category> objectList;
        HttpHeaders responseHeaders = new HttpHeaders();
        requestLog(request);

        objectList = service.getAllObjects(ofNullable(status), ofNullable(name), pageSize, page);
        totalRecords = service.getCountAllObjects(ofNullable(status), ofNullable(name));

        responseHeaders.set("X-Total-Count", totalRecords.toString());
        responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
        return new ResponseEntity<Object>(objectList, responseHeaders, HttpStatus.OK);
    }

    @Operation(summary = "Get a category by category id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found the category", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))),
            @ApiResponse(responseCode = "400", description = "Wrong request", content = @Content(schema = @Schema(implementation = CustomRuntimeException.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = CustomRuntimeException.class))) })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(
            @Parameter(description = "id of category to be searched") @PathVariable("id") Long id,
            HttpServletRequest request) {

        Category object;
        HttpHeaders responseHeaders = new HttpHeaders();
        requestLog(request);

        if (null == id) {
            throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "Wrong request", "There is a to the param.");
        } else {
            object = service.getObjectById(ofNullable(id));
            if (null == object) {
                throw new CustomRuntimeException(HttpStatus.NOT_FOUND, 404, "Not found",
                        "There isn't records to the param");
            }
        }

        responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
        return new ResponseEntity<Object>(object, responseHeaders, HttpStatus.OK);
    }

    private synchronized void requestLog(HttpServletRequest request) {
        AElog.info1(logger,
                util.getInetAddressPort() + " <= " + request.getRemoteHost() + " {method:" + request.getMethod()
                        + ", URI:" + request.getRequestURI() + ", query:" + request.getQueryString() + "}");
    }
}
