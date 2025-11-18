<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books List</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f5f7fa;
            padding: 20px;
        }
        
        .container {
            max-width: 1400px;
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.1);
            padding: 40px;
        }
        
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 3px solid #667eea;
        }
        
        h1 {
            color: #667eea;
            font-size: 2.5em;
        }
        
        .btn {
            padding: 12px 24px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            text-decoration: none;
            font-size: 1em;
            transition: all 0.3s;
            display: inline-block;
        }
        
        .btn-primary {
            background: #667eea;
            color: white;
        }
        
        .btn-primary:hover {
            background: #5568d3;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102,126,234,0.4);
        }
        
        .btn-edit {
            background: #48bb78;
            color: white;
            padding: 8px 16px;
            font-size: 0.9em;
        }
        
        .btn-edit:hover {
            background: #38a169;
        }
        
        .btn-delete {
            background: #f56565;
            color: white;
            padding: 8px 16px;
            font-size: 0.9em;
        }
        
        .btn-delete:hover {
            background: #e53e3e;
        }
        
        .btn-home {
            background: #718096;
            color: white;
            margin-right: 10px;
        }
        
        .btn-home:hover {
            background: #4a5568;
        }
        
        .alert {
            padding: 15px 20px;
            border-radius: 8px;
            margin-bottom: 20px;
            font-weight: 500;
        }
        
        .alert-success {
            background: #c6f6d5;
            color: #22543d;
            border-left: 4px solid #48bb78;
        }
        
        .alert-error {
            background: #fed7d7;
            color: #742a2a;
            border-left: 4px solid #f56565;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        
        thead {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        
        th {
            padding: 15px;
            text-align: left;
            font-weight: 600;
        }
        
        td {
            padding: 15px;
            border-bottom: 1px solid #e2e8f0;
        }
        
        tbody tr {
            transition: background 0.3s;
        }
        
        tbody tr:hover {
            background: #f7fafc;
        }
        
        .actions {
            display: flex;
            gap: 10px;
        }
        
        .no-data {
            text-align: center;
            padding: 40px;
            color: #718096;
            font-size: 1.2em;
        }
        
        .description {
            max-width: 300px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>📖 Books</h1>
            <div>
                <a href="${pageContext.request.contextPath}/" class="btn btn-home">Home</a>
                <a href="${pageContext.request.contextPath}/books/new" class="btn btn-primary">+ Add New Book</a>
            </div>
        </div>
        
        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>
        
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>
        
        <c:choose>
            <c:when test="${empty books}">
                <div class="no-data">No books found. Add your first book!</div>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>ISBN</th>
                            <th>Genre</th>
                            <th>Year</th>
                            <th>Pages</th>
                            <th>Author</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${books}" var="book">
                            <tr>
                                <td>${book.id}</td>
                                <td>${book.title}</td>
                                <td>${book.isbn}</td>
                                <td>${book.genre}</td>
                                <td>${book.publicationYear}</td>
                                <td>${book.pageCount}</td>
                                <td>${book.author.name}</td>
                                <td>
                                    <div class="actions">
                                        <a href="${pageContext.request.contextPath}/books/edit/${book.id}" 
                                           class="btn btn-edit">Edit</a>
                                        <a href="${pageContext.request.contextPath}/books/delete/${book.id}" 
                                           class="btn btn-delete" 
                                           onclick="return confirm('Are you sure you want to delete this book?')">Delete</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>