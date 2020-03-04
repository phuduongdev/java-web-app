<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="#" class="navbar-brand">Todo App</a>
        </div>

        <ul class="navbar-nav">
            <li>
                <a href="<%=request.getContextPath()%>/list" class="nav-link">Todos</a>
            </li>
        </ul>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li>
                <a href="<%=request.getContextPath()%>/logout" class="nav-link">Logout</a>
            </li>
        </ul>
    </nav>
</header>