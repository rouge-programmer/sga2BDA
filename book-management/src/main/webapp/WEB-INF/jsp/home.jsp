<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library Management System</title>
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
            max-width: 800px;
            width: 100%;
            background: white;
            border-radius: 20px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
            padding: 50px;
            text-align: center;
        }
        
        h1 {
            color: #667eea;
            font-size: 3em;
            margin-bottom: 20px;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.1);
        }
        
        p {
            color: #666;
            font-size: 1.2em;
            margin-bottom: 40px;
        }
        
        .card-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 30px;
            margin-top: 40px;
        }
        
        .card {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border-radius: 15px;
            padding: 30px;
            color: white;
            text-decoration: none;
            transition: transform 0.3s, box-shadow 0.3s;
            box-shadow: 0 10px 20px rgba(0,0,0,0.2);
        }
        
        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 15px 30px rgba(0,0,0,0.3);
        }
        
        .card h2 {
            font-size: 1.8em;
            margin-bottom: 15px;
        }
        
        .card p {
            color: rgba(255,255,255,0.9);
            font-size: 1em;
            margin: 0;
        }
        
        .icon {
            font-size: 3em;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>📚 Library Management System</h1>
        <p>Manage your books and authors efficiently</p>
        
        <div class="card-container">
            <a href="${pageContext.request.contextPath}/books" class="card">
                <div class="icon">📖</div>
                <h2>Books</h2>
                <p>View and manage all books</p>
            </a>
            
            <a href="${pageContext.request.contextPath}/authors" class="card">
                <div class="icon">✍️</div>
                <h2>Authors</h2>
                <p>View and manage all authors</p>
            </a>
            
            <a href="${pageContext.request.contextPath}/books/with-authors" class="card">
                <div class="icon">🔗</div>
                <h2>Books & Authors</h2>
                <p>View books with author details</p>
            </a>
        </div>
    </div>
</body>
</html>