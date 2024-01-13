<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/LogInStyle.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>

    </head>
    <body>
        <div class="main">
            <div>
                <h2>Welcome to Kopi Mesin</h2>
                <div class="image"><img src="icon/logo.png" alt=""></div>
            </div>
            <div>
                <form action="staffServlet" >
                    <input type="hidden" name="operation" value="login"/>
                    <h1>USER LOGIN </h1>
                    <label>
                    <i class="far fa-id-card" style="color: #000000;"></i>
                    Username
                    </label>
                    <input type="email" name="username" placeholder=" Enter Username" required>
                    
                    <label>
                    <i class="fas fa-unlock-alt" style="color: #000000;"></i>
                    Password
                    </label>
                    <input type="password" name="password" placeholder=" Enter Password" required>
                    
                    <label>
                    <i class="fas fa-users" style="color: #000000;"></i>
                    Role
                    </label>
                    <select name="role">
                        <option value="manager"> Manager</option>
                        <option value="cashier"> Cashier</option>
                        <option value="cook"> Cook</option>
                    </select>
                    <input type="submit" value="Log In">
                </form>
            </div>
        </div>
    </body>
</html>