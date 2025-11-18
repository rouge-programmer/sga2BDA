<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${author.id == null ? 'Add' : 'Edit'} Author</title>
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
            max-width: 600px;
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
        
        .form-group {
            margin-bottom: 20px;
        }
        
        label {
            display: block;
            color: #4a5568;
            font-weight: 600;
            margin-bottom: 8px;
        }
        
        input, textarea {
            width: 100%;
            padding: 12px;
            border: 2px solid #e2e8f0;
            border-radius: 8px;
            font-size: 1em;
            transition: border-color 0.3s;
        }
        
        input:focus, textarea:focus {
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
        <h1>${author.id == null ? '➕ Add New Author' : '✏️ Edit Author'}</h1>
        
        <c:if test="${not empty error}">
            <div class="alert">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/authors/${author.id == null ? '' : 'update/'}${author.id == null ? '' : author.id}" 
              method="post">
            
            <div class="form-group">
                <label for="name">Name *</label>
                <input type="text" id="name" name="name" 
                       value="${author.name}" required>
            </div>
            
            <div class="form-group">
                <label for="nationality">Nationality</label>
                <input type="text" id="nationality" name="nationality" 
                       value="${author.nationality}">
            </div>
            
            <div class="form-group">
                <label for="birthYear">Birth Year</label>
                <input type="number" id="birthYear" name="birthYear" 
                       value="${author.birthYear}" min="1000" max="2100">
            </div>
            
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" 
                       value="${author.email}">
            </div>
            
            <div class="form-group">
                <label for="biography">Biography</label>
                <textarea id="biography" name="biography">${author.biography}</textarea>
            </div>
            
            <div class="button-group">
                <button type="submit" class="btn btn-submit">
                    ${author.id == null ? 'Create' : 'Update'} Author
                </button>
                <a href="${pageContext.request.contextPath}/authors" 
                   class="btn btn-cancel" style="text-align: center; line-height: 14px;">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>