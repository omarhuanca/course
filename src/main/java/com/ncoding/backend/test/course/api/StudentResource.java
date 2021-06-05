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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncoding.backend.test.course.core.Student;
import com.ncoding.backend.test.course.dto.StudentDTOV;
import com.ncoding.backend.test.course.service.StudentService;
import com.ncoding.backend.test.course.util.AEutil;
import com.ncoding.backend.test.course.util.AElog;
import com.ncoding.backend.test.course.util.exception.response.custom.CustomRuntimeException;

@RestController
@RequestMapping("/api/v1/students")
public class StudentResource {

    private final Logger logger = LoggerFactory.getLogger(StudentResource.class);

    @Autowired
    private AEutil util;

    @Autowired
    private StudentService service;

    @GetMapping
    public ResponseEntity<Object> findAllObjects(@Nullable Integer status, @Nullable String firstName,
            @Nullable String lastName, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "page", defaultValue = "0") Integer page, HttpServletRequest request) {

        Integer totalRecords;
        List<Student> objectList;
        HttpHeaders responseHeaders = new HttpHeaders();
        requestLog(request);

        objectList = service.getAllObjects(ofNullable(status), ofNullable(firstName), ofNullable(lastName), pageSize,
                page);
        totalRecords = service.getCountAllObjects(ofNullable(status), ofNullable(firstName), ofNullable(lastName));

        responseHeaders.set("X-Total-Count", totalRecords.toString());
        responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
        return new ResponseEntity<Object>(objectList, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id, HttpServletRequest request) {

        Student object;
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

    @PostMapping
    public ResponseEntity<Object> saveObject(@Valid @RequestBody StudentDTOV objectDTOV, HttpServletRequest request) {

        Student object = new Student();
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

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateObject(@Valid @RequestBody StudentDTOV objectDTOV, @PathVariable("id") Long id,
            HttpServletRequest request) {

        Student object = new Student();
        HttpHeaders responseHeaders = new HttpHeaders();
        requestLog(request);

        if (objectDTOV != null && id != null) {
            object = service.getObjectById(ofNullable(id));
            if (object != null && object.getUid() != null) {
                objectDTOV.copyCoreObject(object);
                object = service.update(object);
            } else {
                throw new CustomRuntimeException(HttpStatus.NOT_FOUND, 404, "Operacion fallida",
                        "No existe el registro que desea actualizar.");
            }
        } else {
            throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "Solicitud incorrecta",
                    "Existe un error en el/los parámetro(s).");
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
