<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                    <meta name="description" content="" />
                    <meta name="author" content="" />
                    <title>Detail Order ${id}</title>
                    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css"
                        rel="stylesheet" />
                    <link href="/css/styles.css" rel="stylesheet" />
                    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                        crossorigin="anonymous"></script>
                </head>

                <body class="sb-nav-fixed">
                    <jsp:include page="../layout/header.jsp" />
                    <div id="layoutSidenav">
                        <jsp:include page="../layout/sidebar.jsp" />
                        <div id="layoutSidenav_content">
                            <main>
                                <div class="container-fluid px-4">
                                    <h1 class="mt-4">Detail Order</h1>
                                    <ol class="breadcrumb mb-4">
                                        <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                        <li class="breadcrumb-item active">Detail Order</li>
                                    </ol>
                                    <div class="container mt-5">
                                        <div class="row">
                                            <div class="col-12 mx-auto">
                                                <div class="d-flex justify-content-between">
                                                    <h3>Detail Order with id=${id}</h3>
                                                </div>
                                                <hr>
                                                <div class="card" style="width: 60%;">
                                                    <div class="card-header">
                                                        Order Infomation
                                                    </div>
                                                    <div class="col-12 mx-auto">
                                                        <table class="table table-bordered table-hover col-12">
                                                            <thead>
                                                                <tr>
                                                                    <th>Product</th>
                                                                    <th>Name</th>
                                                                    <th>Price</th>
                                                                    <th>Quantity</th>
                                                                    <th>TotalPrice</th>

                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach var="orderDetail" items="${orderDetails}">
                                                                    <tr>
                                                                        <td>
                                                                            <img class="card-img-top"
                                                                                src="/images/product/${orderDetail.product.image}"
                                                                                alt="anh san pham">
                                                                        </td>
                                                                        <td>
                                                                            <a
                                                                                href="/admin/product/${orderDetail.product.id}">${orderDetail.product.name}</a>
                                                                        </td>
                                                                        <td>
                                                                            <fmt:formatNumber type="number"
                                                                                value="${orderDetail.product.price}" />đ
                                                                        </td>
                                                                        <td>${orderDetail.quantity}</td>
                                                                        <td>
                                                                            <fmt:formatNumber type="number"
                                                                                value="${orderDetail.price}" />đ
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <a href="/admin/order" class="btn btn-success mt-5">Back</a>
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