<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <title>Reset Password</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="bg-primary">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-5">
                            <div class="card shadow-lg border-0 rounded-lg mt-5">
                                <div class="card-header">
                                    <h3 class="text-center font-weight-light my-4">Reset Password</h3>
                                </div>
                                <div class="card-body">
                                    <form:form method="post" action="/reset-password" modelAttribute="newResetPassword">
                                        <input type="hidden" name="token" value="${token}" />
                                        <c:set var="errorResetPassword">
                                            <form:errors path="resetPassword" cssClass="invalid-feedback" />
                                        </c:set>
                                        <div class="form-floating mb-3">
                                            <form:input
                                                class="form-control ${not empty errorResetPassword ? 'is-invalid' : ''}"
                                                path="resetPassword" type="password" />
                                            <label for="password">New Password</label>
                                            ${errorResetPassword}
                                        </div>
                                        <div class="mt-4 mb-0">
                                            <button class="btn btn-primary btn-block" type="submit">Reset
                                                Password</button>
                                        </div>
                                        <c:if test="${not empty error}">
                                            <div class="my-2" style="color: red;">${error}</div>
                                        </c:if>
                                        <c:if test="${not empty message}">
                                            <div class="my-2" style="color: green;">${message}</div>
                                        </c:if>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
            </body>

            </html>