<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="" />
                <meta name="author" content="" />
                <title>Update User</title>
                <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        const avatarImage = "${newUser.avatar}";
                        if (avatarImage) {
                            const imgURL = "/images/avatar/" + avatarImage;
                            $("#avatarPreview").attr("src", imgURL);
                            $("#avatarPreview").css({ "display": "block" });
                        }
                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURL);
                            $("#avatarPreview").css({ "display": "block" });
                        });
                    });
                </script>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Update User</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Update User</li>
                                </ol>
                                <div class="container mt-5">
                                    <div class="row">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h1>Update a User</h1>
                                            <hr>
                                            <div class="form-user">
                                                <form:form method="post" action="/admin/user/update"
                                                    modelAttribute="newUser" enctype="multipart/form-data">

                                                    <div class="mb-3" style="display: none;">
                                                        <label class="form-label">ID</label>
                                                        <form:input type="text" class="form-control" path="id" />
                                                    </div>
                                                    <div class="mb-3" style="display: none;">
                                                        <label class="form-label">Pasword</label>
                                                        <form:input type="password" class="form-control"
                                                            path="password" />
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label">Email address</label>
                                                        <form:input type="email" class="form-control" path="email"
                                                            readonly="true" />
                                                        <div id="emailHelp" class="form-text">We'll never share your
                                                            email with anyone
                                                            else.
                                                        </div>
                                                    </div>

                                                    <div class="mb-3">
                                                        <c:set var="errorPhone">
                                                            <form:errors path="phone" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Phone number</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorPhone ? 'is-invalid' : ''}"
                                                            path="phone" />
                                                        ${errorPhone}
                                                    </div>
                                                    <div class="mb-3">
                                                        <c:set var="errorFullName">
                                                            <form:errors path="fullName" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Full name</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorFullName ? 'is-invalid' : ''}"
                                                            path="fullName" />
                                                        ${errorFullName}
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label">Address</label>
                                                        <form:input type="text" class="form-control" path="address" />

                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="" class="form-label">Role</label>
                                                        <form:select class="form-select" path="role.name">
                                                            <form:option value="ADMIN">ADMIN</form:option>
                                                            <form:option value="USER">USER</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label for="avatarFile" class="form-label">Avatar</label>
                                                        <!-- khong dung thuoc tinh path, vi trong user ko co
                                                    thuoc tinh co kieu du lieu la file -->
                                                        <input type="file" class="form-control" id="avatarFile"
                                                            accept=".png, .jpg, .jpeg" name="avatarfile" />
                                                    </div>
                                                    <div class="mb-3 col-12">
                                                        <img style="max-height: 250px; display: none;"
                                                            alt="avatar preview" id="avatarPreview" />
                                                    </div>
                                                    <div class="d-flex justify-content-between">
                                                        <button type="submit" class="btn btn-warning"
                                                            autofocus>Update</button>
                                                        <a href="/admin/user" class="btn btn-success">Back</a>
                                                    </div>

                                                </form:form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp" />
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>

            </body>

            </html>