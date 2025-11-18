<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${book.id == null ? 'Add' : 'Edit'} Book</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        
        .container {
            max-width: 700px;
            width: 100%;
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            padding: 40px;
        }
        
        h1 {
            color: #667eea;
            font-size: 2em;
            margin-bottom: 30px;
            text-align: center;
        }
        
        .form-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            margin-bottom: 20px;
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        .form-group.full-width {
            grid-column: 1 / -1;
        }
        
        label {
            display: block;
            color: #4a5568;
            font-weight: 600;
            margin-bottom: 8px;
        }
        
        input, textarea, select {
            width: 100%;
            padding: 12px;
            border: 2px solid #e2e8f0;
            border-radius: 8px;
            font-size: 1em;
            transition: border-color 0.3s;
        }
        
        input:focus, textarea:focus, select:focus {
            outline: none;
            border-color: #667eea;
        }
        
        textarea {
            resize: vertical;
            min-height: 100px;
        }
        
        .button-group {
            display: flex;
            gap: 15px;
            margin-top: 30px;
        }
        
        .btn {
            flex: 1;
            padding: 14px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 1em;
            font-weight: 600;
            transition: all 0.3s;
        }
        
        .btn-submit {
            background: #667eea;
            color: white;
        }
        
        .btn-submit:hover {
            background: #5568d3;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102,126,234,0.4);
        }
        
        .btn-cancel {
            background: #e2e8f0;
            color: #4a5568;
        }
        
        .btn-cancel:hover {
            background: #cbd5e0;
        }
        
        .alert {
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 20px;
            border-left: 4px solid #f56565;
            background: #fed7d7;
            color: #742a2a;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${book.id == null ? '➕ Add New Book' : '✏️ Edit Book'}</h1>
        
        <c:if test="${not empty error}">
            <div class="alert">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/books/${book.id == null ? '' : 'update/'}${book.id == null ? '' : book.id}" 
              method="post">
            
            <div class="form-group">
                <label for="title">Title *</label>
                <input type="text" id="title" name="title" 
                       value="${book.title}" required>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="isbn">ISBN *</label>
                    <input type="text" id="isbn" name="isbn" 
                           value="${book.isbn}" required>
                </div>
                
                <div class="form-group">
                    <label for="publicationYear">Publication Year</label>
                    <input type="number" id="publicationYear" name="publicationYear" 
                           value="${book.publicationYear}" min="1000" max="2100">
                </div>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="genre">Genre</label>
                    <input type="text" id="genre" name="genre" 
                           value="${book.genre}">
                </div>
                
                <div class="form-group">
                    <label for="pageCount">Page Count</label>
                    <input type="number" id="pageCount" name="pageCount" 
                           value="${book.pageCount}" min="1">
                </div>
            </div>
            
            <div class="form-group">
                <label for="authorId">Author *</label>
                <select id="authorId" name="authorId" required>
                    <option value="">-- Select Author --</option>
                    <c:forEach items="${authors}" var="author">
                        <option value="${author.id}" 
                                ${book.author != null && book.author.id == author.id ? 'selected' : ''}>
                            ${author.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description">${book.description}</textarea>
            </div>
            
            <div class="button-group">
                <button type="submit" class="btn btn-submit">
                    ${book.id == null ? 'Create' : 'Update'} Book
                </button>
                <a href="${pageContext.request.contextPath}/books" 
                   class="btn btn-cancel" style="text-align: center; line-height: 14px;">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>