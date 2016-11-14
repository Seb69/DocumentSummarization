package com.kyushu.autosum.weblayer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by on 01.02.16.
 *
 * @author David Steiman
 */
@RestController
@RequestMapping("/foo")
public class WebController {

    private static final Logger LOG = LoggerFactory.getLogger(WebController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String readFoo(@RequestHeader(value="Authorization") String bearer) {

        bearer = bearer.substring(7);

        Jwt jwt = JwtHelper.decode(bearer);
        LOG.warn(jwt.getClaims());
        return "read foo " + UUID.randomUUID().toString();
    }

    @PreAuthorize("hasAuthority('FOO_WRITE')")
    @RequestMapping(method = RequestMethod.POST)
    public String writeFoo() {
        return "write foo " + UUID.randomUUID().toString();
    }
}
