<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Update User</title>
                <!-- Latest compiled and minified CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

                <style>
                    * {
                        margin: 0px;
                        border: 0;
                        box-sizing: border-box;
                    }

                    /* .container {
                    position: absolute;
                    width: 50%;
                    top: 50px;
                    left: 50%;
                    transform: translateX(-50%);
                } */
                </style>
            </head>

            <body>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h1>Update a User</h1>
                            <hr>
                            <div class="form-user">
                                <form:form method="post" action="/admin/user/update" modelAttribute="newUser">
                                    <div class="mb-3" style="display: none;">
                                        <label class="form-label">ID</label>
                                        <form:input type="text" class="form-control" path="id" />
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Email address</label>
                                        <form:input type="email" class="form-control" path="email" disabled="true" />
                                        <div id="emailHelp" class="form-text">We'll never share your email with anyone
                                            else.
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label">Phone number</label>
                                        <form:input type="text" class="form-control" path="phone" />

                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Full name</label>
                                        <form:input type="text" class="form-control" path="fullName" />

                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Address</label>
                                        <form:input type="text" class="form-control" path="address" />

                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <button type="submit" class="btn btn-warning" autofocus>Update</button>
                                        <a href="/admin/user" class="btn btn-success">Back</a>
                                    </div>

                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </body>

            </html>