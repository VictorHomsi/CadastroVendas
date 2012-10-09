<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Vendas</title>
    </head>
    <body>
        <h1> Cadastro de Usuarios </h1>

        <!Form só vai dar submit se a funcao de validar campos retornar true>
    <form action="cadastroUsuarios.htm" method="post" onsubmit='return validaCampo()'>
        Nome Usuario: 
        <input id="nome" type="text" name="nome"/>
        <br/>
        <p><input type="submit" value="Cadastrar !"/></p>

        <table border="1">
            <tr>
                <th>Nome Usuario</th>
            </tr>
            <c:forEach var="usuario" items="${usuarios}">
                <tr>
                    <th><c:out value="${usuario.nomeUsuario}"/></th>
                </tr>
            </c:forEach>
        </table>
    </form>    

    <p>
        <a href="index.htm">Voltar</a>
    </p>

    <!Funcao para validar campos obrigatórios>
    <script language="javascript">
        function validaCampo(){
            if(document.getElementById('nome').value == "") {
                alert("O nome do usuario é obrigatório !");
                return false;
            }
            alert("Usuario cadastrado com sucesso !");
            return true;
        }
    </script>
</body>
</html>
