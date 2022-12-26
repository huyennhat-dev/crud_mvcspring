<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
                <meta http-equiv="X-UA-Compatible" content="ie=edge">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
                <title>Document</title>
                <style>
                    .ck.ck-content{
                        min-height:200px
                    }
                    .ck.ck-editor__top{
                        display:none
                    }
                </style>
            </head>

            <body>
                <div class="container mt-4">
                    <%--@elvariable id="product" type=""--%>
                        <form:form method="POST" action="/admin/products/edit/${product.get().getId()}" modelAttribute="product" enctype="multipart/form-data">
                            <div class="form-group">
                                <form:label path="productName">Tên sản phẩm</form:label>
                                <form:input type="text" class="form-control" path="productName" placeholder="Nhập tên sản phẩm..." />
                            </div>
                            <div class="form-group">
                                <label>Hình ảnh</label>
                                <div class="row d-flex align-items-center m-0">
                                    <div id="img" class="col-2">
                                        <img src="${product.get().getProductPhoto()}" class="img-thumbnail " >
                                    </div>
                                    <input type="file" id="fileUpload" accept="image/*" name="image"  class="form-control-file col-10"  />

                                </div>
                            </div>

                            <div class="form-group">
                                <form:label path="price">Giá sản phẩm</form:label>
                                <form:input type="number" class="form-control" path="price" placeholder="Nhập giá sản phẩm..." />
                            </div>
                            <div class="form-group">
                                <form:label path="quantity">Số lượng sản phẩm</form:label>
                                <form:input type="number" class="form-control" path="quantity" placeholder="Nhập số lượng sản phẩm..." />
                            </div>
                            <div class="form-group">
                                <form:label path="categoryID">Danh mục</form:label>
                                <form:select path="categoryID" class="form-control">
                                    <c:forEach var="category" items="${categories}">
                                        <form:option value="${category.getId()}">
                                            ${category.getCategoryName()}
                                        </form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <form:label path="description">Mô tả sản phẩm</form:label>
                                <form:textarea id="editor" class="form-control" path="description"></form:textarea>
                            </div>

                            <div class="form-group">
                                <form:label path="status">Trạng thái</form:label>
                                <form:select path="status" class="form-control">
                                    <form:option value="0">Active</form:option>
                                    <form:option value="1">Un-active</form:option>
                                </form:select>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form:form>
                </div>
                <script src="https://cdn.ckeditor.com/ckeditor5/35.4.0/classic/ckeditor.js"></script>
                <script>
                    ClassicEditor
                        .create( document.querySelector( '#editor' ) )
                        .catch( error => {
                            console.error( error );
                        } );
                </script>
            </body>

            </html>