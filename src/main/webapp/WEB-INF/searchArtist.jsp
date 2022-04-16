<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
    <!-- Here we have to import the Date class. -->
    <!-- You will put the import in the first line of the jsp tag. Use the import attribute -->

    <!-- c:out ; c:forEach ; c:if -->
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!-- Formatting (like dates) -->
        <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <!-- form:form -->
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <!-- for rendering errors on PUT routes -->
                <%@ page isErrorPage="true" %>

                    <!DOCTYPE html>

                    <html>

                    <head>
                        <meta charset="UTF-8" />
                        <title>Craig Burke - Lookify - Search for Artist Results</title>
                        <!-- Bootstrap -->
                        <!-- CSS only -->
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                            rel="stylesheet"
                            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
                            crossorigin="anonymous" />
                        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
                            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
                            crossorigin="anonymous"></script>
                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
                            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
                            crossorigin="anonymous"></script>
                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
                        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
                    </head>

                    <body>
                        <!-- Buttons -->
                        <div class="container w-50">
                            <!-- == Delete button == -->
                            <!-- <p class="w-100"></p>
                            <a class="btn btn-danger float-end mb-3 me-3" href="/lookify/delete/${song.id}">Delete</a>
                            <p class="w-100"></p> -->
                            <!-- == Dashboard button == -->
                            <p class="w-100"></p>
                            <a class="btn btn-primary float-end mb-3 me-3" href="/lookify/dashboard">Dashboard</a>
                            <p class="w-100"></p>
                            <!-- == Update button == -->
                            <p class="w-100"></p>
                            <a class="btn btn-warning float-end mb-3 me-3" href="/lookify/newSong">Create Song</a>
                            <p class="w-100"></p>
                            <!-- == Top Ten button == -->
                            <p class="w-100"></p>
                            <a class="btn btn-success float-end mb-3 me-3" href="/lookify/topTen">Top Ten</a>
                            <p class="w-100"></p>
                            <!-- == ShowOne button == -->
                            <!-- <p class="w-100"></p>
                                <a class="btn btn-success float-end mb-3 me-3" href="/lookify/showSong">Show Song</a>
                                <p class="w-100"></p> -->

                        </div>

                        <!-- Beginning of Container -->
                        <div class="container w-50">

                            <p class="w-100"></p>
                            <h1 class="bg-primary text-light ps-3">Lookify</h1>
                            <p class="w-100"></p>
                            <h3 class="bg-primary text-light ps-3">Songs by artist: ${searchQuery}</h3>
                            <p class="w-100"></p>
                            <!-- == Search button == -->

                            <!-- == Search button == -->
                            <p class="w-100"></p>
                            <form action="/lookify/searchArtist" method="post">
                                <div class="form-group">
                                    <label for="searchQuery">Search</label>
                                    <input type="text" class="form-control" id="searchQuery" name="searchQuery"
                                        placeholder="Search Artist">
                                </div>
                                <button type="submit" class="btn btn-primary">New Search</button>
                            </form>
                            <p class="w-100"></p>

                            <!-- == table == -->
                            <p class="w-100"></p>
                            <div class="container bg-primary text-dark rounded-3">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col" class="h4 text-center">Rating</th>
                                            <th scope="col" class="h4 text-center">Title</th>
                                            <th scope="col" class="h4 text-center">Artist</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="song" items="${songs}">
                                            <tr>
                                                <td scope="row">
                                                    <p class="text-center">
                                                        <c:out value="${song.rating}"></c:out>

                                                    </p>
                                                </td>
                                                <td scope="row">
                                                    <a href="/lookify/showSong/${song.id}">
                                                        <p class="text-center">
                                                            <c:out value="${song.title}"></c:out>
                                                        </p>
                                                    </a>
                                                </td>
                                                <td scope="row">
                                                    <p class="text-center">
                                                        <c:out value="${song.artist}"></c:out>
                                                    </p>
                                                </td>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <p class="w-100"></p>
                            <!-- End of table -->

                        </div>
                        <!-- End of Container -->

                    </body>

                    </html>