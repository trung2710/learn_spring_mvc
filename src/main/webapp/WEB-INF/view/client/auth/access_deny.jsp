<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8">
                <title>404 Not Found</title>
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

            <body>

                <!-- Spinner Start -->
                <div id="spinner"
                    class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
                    <div class="spinner-grow text-primary" role="status"></div>
                </div>
                <!-- Spinner End -->


                <!-- Navbar start -->
                <div class="container-fluid fixed-top">
                    <div class="container topbar bg-primary d-none d-lg-block">
                        <div class="d-flex justify-content-between">
                            <div class="top-info ps-2">
                                <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a
                                        href="#" class="text-white">123 Street, New York</a></small>
                                <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#"
                                        class="text-white">Email@Example.com</a></small>
                            </div>
                            <div class="top-link pe-2">
                                <a href="#" class="text-white"><small class="text-white mx-2">Privacy
                                        Policy</small>/</a>
                                <a href="#" class="text-white"><small class="text-white mx-2">Terms of Use</small>/</a>
                                <a href="#" class="text-white"><small class="text-white ms-2">Sales and
                                        Refunds</small></a>
                            </div>
                        </div>
                    </div>
                    <div class="container px-0">
                        <nav class="navbar navbar-light bg-white navbar-expand-xl">
                            <a href="/homepage" class="navbar-brand">
                                <h1 class="text-primary display-6">LaptopShop</h1>
                            </a>
                            <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarCollapse">
                                <span class="fa fa-bars text-primary"></span>
                            </button>

                        </nav>
                    </div>
                </div>
                <!-- Navbar End -->

                <!-- 404 Start -->
                <div class="container-fluid py-5">
                    <div class="container py-5 text-center">
                        <div class="row justify-content-center">
                            <div class="col-lg-6">
                                <i class="bi bi-exclamation-triangle display-1 text-secondary"></i>
                                <h1 class="display-1">404</h1>
                                <h1 class="mb-4">Page Not Found</h1>
                                <p class="mb-4">We’re sorry, the page you have looked for does not exist in our website!
                                    Maybe go to our home page or try to use a search?</p>
                                <a class="btn border-secondary rounded-pill py-3 px-5" href="/homepage">Go Back To
                                    Home</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 404 End -->

                <jsp:include page="../layout/footer.jsp" />

                <!-- Back to Top -->
                <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
                        class="fa fa-arrow-up"></i></a>


                <!-- JavaScript Libraries -->
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                <script src="/client/lib/easing/easing.min.js"></script>
                <script src="/client/lib/waypoints/waypoints.min.js"></script>
                <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
                <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

                <!-- Template Javascript -->
                <script src="/client/js/main.js"></script>
            </body>

            </html>