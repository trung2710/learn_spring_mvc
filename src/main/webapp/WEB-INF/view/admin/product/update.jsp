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
                <title>Update Product</title>
                <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        const imageFile = $("#imageFile");
                        const orgImage = "${newProduct.image}";
                        if (orgImage) {
                            const urlImage = "/images/product/" + orgImage;
                            $("#imagePreview").attr("src", urlImage);
                            $("#imagePreview").css({ "display": "block" });
                        }
                        imageFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#imagePreview").attr("src", imgURL);
                            $("#imagePreview").css({ "display": "block" });
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
                                <h1 class="mt-4">Update A Product</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Update Product</li>
                                </ol>
                                <div class="container mt-5">
                                    <div class="row">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h1>Update a Product</h1>
                                            <hr>
                                            <div class="form-product">
                                                <form:form method="post" action="/admin/product/update"
                                                    modelAttribute="newProduct" class="row"
                                                    enctype="multipart/form-data">
                                                    <c:set var="errorName">
                                                        <form:errors path="name" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <c:set var="errorPrice">
                                                        <form:errors path="price" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <c:set var="errorDetailDesc">
                                                        <form:errors path="detailDesc" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <c:set var="errorShortDesc">
                                                        <form:errors path="shortDesc" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <c:set var="errorQuantity">
                                                        <form:errors path="quantity" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <div class="mb-3 col-12 col-md-6" style="display: none;">
                                                        <label class="form-label">ID</label>
                                                        <form:input type="text" class="form-control" path="id" />
                                                    </div>
                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label class="form-label">Name</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorName ? 'is-invalid' : ''}"
                                                            path="name" />
                                                        ${errorName}
                                                    </div>
                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label class="form-label">Price</label>
                                                        <form:input type="number"
                                                            class="form-control ${not empty errorPrice ? 'is-invalid' : ''}"
                                                            path="price" />
                                                        ${errorPrice}
                                                    </div>
                                                    <div class="mb-3 col-12">
                                                        <label class="form-label">Detail description</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorDetailDesc ? 'is-invalid' : ''}"
                                                            path="detailDesc" />
                                                        ${errorDetailDesc}
                                                    </div>
                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label class="form-label">Short description</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorShortDesc ? 'is-invalid' : ''}"
                                                            path="shortDesc" />
                                                        ${errorShortDesc}
                                                    </div>
                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label class="form-label">Quantity</label>
                                                        <form:input type="number"
                                                            class="form-control ${not empty errorQuantity ? 'is-invalid' : ''}"
                                                            path="quantity" />
                                                        ${errorQuantity}
                                                    </div>
                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label class="form-label">Factory</label>
                                                        <form:select class="form-select" path="factory">
                                                            <form:option value="Apple">Apple</form:option>
                                                            <form:option value="Asus">Asus</form:option>
                                                            <form:option value="Lenovo">Lenovo</form:option>
                                                            <form:option value="Hp">Hp</form:option>
                                                            <form:option value="Dell">Dell</form:option>
                                                            <form:option value="Acer">Acer</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label class="form-label">Target</label>
                                                        <form:select class="form-select" path="target">
                                                            <form:option value="Gaming">Gaming</form:option>
                                                            <form:option value="Van Phong">Van Phong</form:option>
                                                            <form:option value="Do Hoa">Do Hoa</form:option>
                                                            <form:option value="Doanh Nhan">Doanh Nhan</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label for="avatarFile" class="form-label">Image </label>
                                                        <!-- khong dung thuoc tinh path, vi trong user ko co
                                                        thuoc tinh co kieu du lieu la file -->
                                                        <input type="file" class="form-control" id="imageFile"
                                                            accept=".png, .jpg, .jpeg" name="imagefile" />
                                                    </div>
                                                    <div class="mb-3 col-12">
                                                        <img style="max-height: 250px; display: none;"
                                                            alt="image preview" id="imagePreview" />
                                                    </div>

                                                    <div class="col-12 mb-5">
                                                        <button type="submit" class="btn btn-primary"
                                                            autofocus>Submit</button>
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