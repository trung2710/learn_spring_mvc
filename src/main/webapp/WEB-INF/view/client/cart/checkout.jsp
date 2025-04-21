<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8">
                    <title>CheckOut</title>
                    <meta content="width=device-width, initial-scale=1.0" name="viewport">
                    <meta content="" name="keywords">
                    <meta content="" name="description">

                    <!-- Google Web Fonts -->
                    <link rel="preconnect" href="https://fonts.googleapis.com">
                    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                    <link
                        href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
                        rel="stylesheet">

                    <!-- Icon Font Stylesheet -->
                    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                        rel="stylesheet">

                    <!-- Libraries Stylesheet -->
                    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
                    <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


                    <!-- Customized Bootstrap Stylesheet -->
                    <link href="/client/css/bootstrap.min.css" rel="stylesheet">

                    <!-- Template Stylesheet -->
                    <link href="/client/css/style.css" rel="stylesheet">
                </head>

                <div>

                    <!-- Spinner Start -->
                    <div id="spinner"
                        class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
                        <div class="spinner-grow text-primary" role="status"></div>
                    </div>
                    <!-- Spinner End -->

                    <jsp:include page="../layout/header.jsp" />



                    <!-- Single Page Header start -->
                    <div class="container-fluid page-header py-5">
                        <h1 class="text-center text-white display-6">Checkout</h1>
                        <ol class="breadcrumb justify-content-center mb-0">
                            <li class="breadcrumb-item"><a href="/homepage">Home</a></li>
                            <li class="breadcrumb-item active text-white">Checkout</li>
                        </ol>
                    </div>
                    <!-- Single Page Header End -->


                    <!-- Checkout Page Start -->
                    <div class="container-fluid py-5">
                        <div class="container py-5">
                            <h1 class="mb-4">Thông tin nhận hàng</h1>
                            <form:form action="/place-order" method="post" modelAttribute="newOrderDto">
                                <div class="row g-5">
                                    <div class="col-md-12 col-lg-6 col-xl-7">
                                        <c:set var="errorName">
                                            <form:errors path="name" cssClass="invalid-feedback" />
                                        </c:set>
                                        <c:set var="errorAddress">
                                            <form:errors path="address" cssClass="invalid-feedback" />
                                        </c:set>
                                        <c:set var="errorPhone">
                                            <form:errors path="phone" cssClass="invalid-feedback" />
                                        </c:set>
                                        <c:set var="errorEmail">
                                            <form:errors path="email" cssClass="invalid-feedback" />
                                        </c:set>
                                        <div class="form-item">
                                            <label class="form-label my-3">Name<sup>*</sup></label>
                                            <form:input type="text"
                                                class="form-control ${not empty errorName ? 'is-invalid' : ''}"
                                                path="name" />
                                            ${errorName}
                                        </div>
                                        <div class="form-item">
                                            <label class="form-label my-3">Address<sup>*</sup></label>
                                            <form:input type="text"
                                                class="form-control ${not empty errorAddress ? 'is-invalid' : ''}"
                                                path="address" />
                                            ${errorAddress}
                                        </div>
                                        <div class="form-item">
                                            <label class="form-label my-3">Phone<sup>*</sup></label>
                                            <form:input type="text"
                                                class="form-control ${not empty errorPhone ? 'is-invalid' : ''}"
                                                path="phone" />
                                            ${errorPhone}
                                        </div>
                                        <div class="form-item">
                                            <label class="form-label my-3">Email<sup>*</sup></label>
                                            <form:input type="email"
                                                class="form-control ${not empty errorEmail ? 'is-invalid' : ''}"
                                                path="email" />
                                            ${errorEmail}
                                        </div>
                                        <div class="form-item">
                                            <form:textarea name="text" class="form-control" spellcheck="false" cols="30"
                                                rows="11" placeholder="Oreder Notes (Optional)" path="desc" />

                                        </div>
                                    </div>
                                </div>
                                <div class="container py-5">
                                    <div class="col-md-12 col-lg-6 col-xl-5">
                                        <div class="table-responsive">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">Products</th>
                                                        <th scope="col">Name</th>
                                                        <th scope="col">Price</th>
                                                        <th scope="col">Quantity</th>
                                                        <th scope="col">Total</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="cartDetail" items="${cartDetails}">
                                                        <tr>
                                                            <th scope="row">
                                                                <div class="d-flex align-items-center mt-2">
                                                                    <img src="/images/product/${cartDetail.product.image}"
                                                                        class="img-fluid rounded-circle"
                                                                        style="width: 90px; height: 90px;" alt="">
                                                                </div>
                                                            </th>
                                                            <td class="py-5">${cartDetail.product.name}</td>
                                                            <td class="py-5">
                                                                <fmt:formatNumber type="number"
                                                                    value="${cartDetail.product.price}" />đ
                                                            </td>
                                                            <td class="py-5" style="margin-left: 20px;">
                                                                ${cartDetail.quantity}</td>
                                                            <td class="py-5">
                                                                <fmt:formatNumber type="number"
                                                                    value="${cartDetail.price}" />đ
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            <div
                                                class="row g-4 text-center align-items-center justify-content-center pt-4">
                                                <button type="submit"
                                                    class="btn border-secondary py-3 px-4 text-uppercase w-100 text-primary">Place
                                                    Order</button>
                                            </div>
                                        </div>
                                    </div>
                            </form:form>
                        </div>
                    </div>
                    </body>
                    <!-- Checkout Page End -->


                    <jsp:include page="../layout/footer.jsp" />



                    <!-- Back to Top -->
                    <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
                            class="fa fa-arrow-up"></i></a>


                    <!-- JavaScript Libraries -->
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                    <script src="/client/lib/waypoints/waypoints.min.js"></script>
                    <script src="/client/lib/easing/easing.min.js"></script>
                    <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
                    <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

                    <!-- Template Javascript -->
                    <script src="/client/js/main.js"></script>/client/
                    </body>

                </html>