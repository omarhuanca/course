package com.ncoding.backend.test.course.api;

import static java.util.Optional.ofNullable;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncoding.backend.test.course.core.Enroll;
import com.ncoding.backend.test.course.dto.EnrollDTOV;
import com.ncoding.backend.test.course.service.EnrollService;
import com.ncoding.backend.test.course.util.AElog;
import com.ncoding.backend.test.course.util.AEutil;
import com.ncoding.backend.test.course.util.exception.response.custom.CustomRuntimeException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/enrolls")
@Tag(name = "enrolls", description = "the enroll API")
public class EnrollResource {

    private final Logger logger = LoggerFactory.getLogger(EnrollResource.class);

    @Autowired
    private AEutil util;

    @Autowired
    private EnrollService service;

    @Operation(summary = "Get all enrolls")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "found enrolls", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Enroll.class))) }),
            @ApiResponse(responseCode = "404", description = "No Students found", content = @Content) })
    @GetMapping
    public ResponseEntity<Object> findAllObjects(@Nullable Integer status, @Nullable Long studentId,
            @Nullable Long courseId, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "page", defaultValue = "0") Integer page, HttpServletRequest request) {

        Integer totalRecords;
        List<Enroll> objectList;
        HttpHeaders responseHeaders = new HttpHeaders();
        requestLog(request);

        objectList = service.getAllObjects(ofNullable(status), ofNullable(studentId), ofNullable(courseId), pageSize,
                page);
        totalRecords = service.getCountAllObjects(ofNullable(status), ofNullable(studentId), ofNullable(courseId));

        responseHeaders.set("X-Total-Count", totalRecords.toString());
        responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
        return new ResponseEntity<Object>(objectList, responseHeaders, HttpStatus.OK);
    }

    @Operation(summary = "Get a enroll by enroll id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found the foo", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Enroll.class)) }),
            @ApiResponse(responseCode = "400", description = "Wrong request", content = @Content(schema = @Schema(implementation = CustomRuntimeException.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = CustomRuntimeException.class))) })
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(
            @Parameter(description = "id of enroll to be searched") @PathVariable("id") Long id,
            HttpServletRequest request) {

        Enroll object;
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

    @Operation(summary = "Create a enroll")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "enroll created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Enroll.class)) }),
            @ApiResponse(responseCode = "400", description = "Wrong Request", content = @Content(schema = @Schema(implementation = CustomRuntimeException.class))) })
    @PostMapping
    public ResponseEntity<Object> saveObject(
            @Parameter(description = "enroll object to be created") @Valid @RequestBody EnrollDTOV objectDTOV,
            HttpServletRequest request) {

        Enroll object = new Enroll();
        HttpHeaders responseHeaders = new HttpHeaders();
        requestLog(request);

        if (null != objectDTOV) {
            objectDTOV.copyCoreObject(object);
            object = service.save(object);
        } else {
            throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "Wrong Request",
                    "The object want to save is null.");
        }

        responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
        return new ResponseEntity<Object>(object, responseHeaders, HttpStatus.OK);
    }

    @Operation(summary = "Update a enroll")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "enroll updated successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Enroll.class)) }),
            @ApiResponse(responseCode = "404", description = "Fail request", content = @Content(schema = @Schema(implementation = CustomRuntimeException.class))),
            @ApiResponse(responseCode = "400", description = "Wrong request", content = @Content(schema = @Schema(implementation = CustomRuntimeException.class))) })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateObject(
            @Parameter(description = "enroll object to be updated") @Valid @RequestBody EnrollDTOV objectDTOV,
            @Parameter(description = "id of enroll to be updated") @PathVariable("id") Long id,
            HttpServletRequest request) {

        Enroll object = new Enroll();
        HttpHeaders responseHeaders = new HttpHeaders();
        requestLog(request);

        if (objectDTOV != null && id != null) {
            object = service.getObjectById(ofNullable(id));
            if (object != null && object.getUid() != null) {
                objectDTOV.copyCoreObject(object);
                object = service.update(object);
            } else {
                throw new CustomRuntimeException(HttpStatus.NOT_FOUND, 404, "Fail request",
                        "There isn't record you want update.");
            }
        } else {
            throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "Wrong request",
                    "There are error belongs params.");
        }

        responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
        return new ResponseEntity<Object>(object, responseHeaders, HttpStatus.OK);
    }

    @Operation(summary = "Delete a enroll")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "enroll deleted"),
            @ApiResponse(responseCode = "404", description = "Fail request", content = @Content(schema = @Schema(implementation = CustomRuntimeException.class))),
            @ApiResponse(responseCode = "400", description = "Wrong request", content = @Content(schema = @Schema(implementation = CustomRuntimeException.class))) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteObject(
            @Parameter(description = "id of enroll to be deleted") @PathVariable("id") Long id,
            HttpServletRequest request) {

        Enroll object = new Enroll();
        HttpHeaders responseHeaders = new HttpHeaders();
        requestLog(request);

        if (null != id) {
            object = service.getObjectById(ofNullable(id));
            if (null != object && null != object.getUid()) {
                service.delete(object);
            } else {
                throw new CustomRuntimeException(HttpStatus.NOT_FOUND, 404, "Fail request",
                        "There isn't record you want delete.");
            }
        } else {
            throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "Wrong request",
                    "There are error belongs params.");
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
