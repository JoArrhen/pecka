/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.struts2.views.jsp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

import com.mockobjects.servlet.MockPageContext;


/**
 */
public class StrutsMockPageContext extends MockPageContext {

    private Map attributes = new HashMap();
    private ServletResponse response;


    public void setAttribute(String s, Object o) {
        if ((s == null) || (o == null)) {
            throw new NullPointerException("PageContext does not accept null attributes");
        }

        this.attributes.put(s, o);
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public Object getAttributes(String key) {
        return this.attributes.get(key);
    }

    public void setResponse(ServletResponse response) {
        this.response = response;
    }

    public ServletResponse getResponse() {
        return response;
    }

    public HttpSession getSession() {
        HttpSession session = super.getSession();

        if (session == null) {
            session = ((HttpServletRequest) getRequest()).getSession(true);
        }

        return session;
    }

    public Object findAttribute(String s) {
        return attributes.get(s);
    }

    public  ExpressionEvaluator getExpressionEvaluator(){
return null;
    }

       public  VariableResolver getVariableResolver(){
return null;
       }

        public  void include(String var1, boolean var2) throws ServletException, IOException{

        }
    public void removeAttribute(String key) {
        this.attributes.remove(key);
    }

}
