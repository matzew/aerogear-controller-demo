/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.aerogear.controller.demo;

import javax.servlet.http.HttpServletResponse;

import org.jboss.aerogear.controller.router.error.ErrorResponse;
import org.jboss.aerogear.controller.router.error.JsonErrorResponse;
import org.jboss.aerogear.controller.router.parameter.MissingRequestParameterException;
import org.jboss.aerogear.controller.router.rest.pagination.PagingRequestException;

public class Error {

    public String index(Exception e) {
        System.out.println("[Demo Error handler :" + e.getMessage());
        return "{exception:" + e.getMessage() + "}";
    }

    public void security() {
    }

    public void throwException() {
        throw new RuntimeException("Demo Exception");
    }

    public ErrorResponse respondWithErrorStatus(final CarNotFoundException e) {
        return new JsonErrorResponse(e.getStatus()).message("error", e.getMessage());
    }
    
    public ErrorResponse handlePagingRequestException(final PagingRequestException e) {
        return new JsonErrorResponse(HttpServletResponse.SC_BAD_REQUEST).message("error", e.getMessage());
    }
    
    public ErrorResponse handleMissingRequestParameter(final MissingRequestParameterException e) {
        return new JsonErrorResponse(HttpServletResponse.SC_BAD_REQUEST).message("error", e.getMessage());
    }
}
