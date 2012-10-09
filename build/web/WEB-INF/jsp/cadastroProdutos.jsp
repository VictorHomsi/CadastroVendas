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
        <h1> Cadastro de Produtos </h1>

        <!Form só vai dar submit se a funcao de validar campos retornar true>
    <form action="cadastroProdutos.htm" method="post" onsubmit='return validaCampo()'>
        Nome Produto: 
        <input id="desc" type="text" name="desc"/>
        <br/>
        Valor:
        <input id="valor" type="text" name="valor" style="margin-left: 59px"/>
        <br/>
        <p><input type="submit" value="Cadastrar !"/></p>

        <table border="1">
            <tr>
                <th>Nome do Produto</th>
                <th>Valor do Produto</th>
            </tr>
            <c:forEach var="produto" items="${produtos}">
                <tr>
                    <th><c:out value="${produto.desc_Produto}"/></th>
                    <th><c:out value="${produto.valor_produto}"/></th>
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
            if(document.getElementById('desc').value == "" ||
                document.getElementById('valor').value == "") {
                alert("Todos os campos são obrigatórios !");
                return false;
            }
            alert("Produto cadastrado com sucesso !");
            return true;
        }
    </script>
</body>
</html>
