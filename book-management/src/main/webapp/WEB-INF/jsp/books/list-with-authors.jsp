<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books with Authors</title>
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
        
        .info-badge {
            background: #e6fffa;
            color: #234e52;
            padding: 10px 20px;
            border-radius: 20px;
            font-size: 0.9em;
            border: 2px solid #81e6d9;
        }
        
        .btn-home {
            background: #718096;
            color: white;
            padding: 12px 24px;
            border: none;
            border-radius: 8px;
            text-decoration: none;
            font-size: 1em;
            transition: all 0.3s;
            display: inline-block;
        }
        
        .btn-home:hover {
            background: #4a5568;
            transform: translateY(-2px);
        }
        
        .cards-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
            gap: 25px;
            margin-top: 20px;
        }
        
        .card {
            background: white;
            border: 2px solid #e2e8f0;
            border-radius: 12px;
            padding: 25px;
            transition: all 0.3s;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
        }
        
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0,0,0,0.15);
            border-color: #667eea;
        }
        
        .card-header {
            border-bottom: 2px solid #e2e8f0;
            padding-bottom: 15px;
            margin-bottom: 15px;
        }
        
        .book-title {
            font-size: 1.4em;
            color: #2d3748;
            font-weight: 700;
            margin-bottom: 5px;
        }
        
        .book-isbn {
            color: #718096;
            font-size: 0.9em;
        }
        
        .card-body {
            margin-bottom: 15px;
        }
        
        .info-row {
            display: flex;
            justify-content: space-between;
            padding: 8px 0;
            border-bottom: 1px solid #f7fafc;
        }
        
        .info-label {
            color: #718096;
            font-weight: 600;
        }
        
        .info-value {
            color: #2d3748;
        }
        
        .author-section {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 15px;
            border-radius: 8px;
            margin-top: 15px;
        }
        
        .author-section h3 {
            font-size: 1.1em;
            margin-bottom: 10px;
            display: flex;
            align-items: center;
            gap: 8px;
        }
        
        .author-info {
            font-size: 0.9em;
            opacity: 0.95;
            line-height: 1.6;
        }
        
        .no-data {
            text-align: center;
            padding: 60px;
            color: #718096;
            font-size: 1.3em;
        }
        
        .genre-badge {
            display: inline-block;
            background: #edf2f7;
            color: #2d3748;
            padding: 5px 12px;
            border-radius: 15px;
            font-size: 0.85em;
            font-weight: 600;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <div>
                <h1>🔗 Books with Authors</h1>
                <div class="info-badge">📊 Using INNER JOIN Query</div>
            </div>
            <a href="${pageContext.request.contextPath}/" class="btn-home">Home</a>
        </div>
        
        <c:choose>
            <c:when test="${empty booksWithAuthors}">
                <div class="no-data">No books with authors found in the database.</div>
            </c:when>
            <c:otherwise>
                <div class="cards-container">
                    <c:forEach items="${booksWithAuthors}" var="bookAuthor">
                        <div class="card">
                            <div class="card-header">
                                <div class="book-title">${bookAuthor.bookTitle}</div>
                                <div class="book-isbn">ISBN: ${bookAuthor.isbn}</div>
                            </div>
                            
                            <div class="card-body">
                                <div class="info-row">
                                    <span class="info-label">Genre:</span>
                                    <span class="genre-badge">${bookAuthor.genre}</span>
                                </div>
                                <div class="info-row">
                                    <span class="info-label">Published:</span>
                                    <span class="info-value">${bookAuthor.publicationYear}</span>
                                </div>
                                <div class="info-row">
                                    <span class="info-label">Pages:</span>
                                    <span class="info-value">${bookAuthor.pageCount}</span>
                                </div>
                                
                                <c:if test="${not empty bookAuthor.description}">
                                    <div style="margin-top: 15px; padding-top: 15px; border-top: 1px solid #e2e8f0;">
                                        <p style="color: #4a5568; font-size: 0.95em; line-height: 1.6;">
                                            ${bookAuthor.description}
                                        </p>
                                    </div>
                                </c:if>
                            </div>
                            
                            <div class="author-section">
                                <h3>✍️ Author Information</h3>
                                <div class="author-info">
                                    <strong>${bookAuthor.authorName}</strong><br>
                                    ${bookAuthor.nationality} • Born ${bookAuthor.birthYear}<br>
                                    📧 ${bookAuthor.email}
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>