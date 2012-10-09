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
        <h1> Cadastro de Venda </h1>
        <!Form só vai dar submit se a funcao de validar campos retornar true>
    <form action="cadastroVendas.htm" method="post" onsubmit='return validaCampos()'>
        Nome Cliente: 
        <select id="nome" name="nome">
            <c:forEach var="usuario" items="${usuarios}">
                <option><c:out value="${usuario.nomeUsuario}" /></option>
            </c:forEach>
        </select>
        <br/>
        Produtos:
        <select id="listProdutos" name="listProdutos" SIZE="5" style="width: 250px; margin-left: 20px" multiple="multiple" onclick="calculaValorTotal()">
            <c:forEach var="produto" items="${produtos}">
                <option>
                    <c:out value="${produto.desc_Produto}" />
                    - Valor : 
                    <c:out value="${produto.valor_produto}" />
                </option>
            </c:forEach>
        </select>
        <br/>
        Valor Total: <input id="valorTotal" type="text" value="0.00" style="margin-top: 10px" disabled="true"></input>

        <p><input type="submit" value="Cadastrar !"/></p>

        <p>
            <a href="index.htm">Voltar</a>
        </p>
    </form>

    <!Funcao para validar campos obrigatórios>
    <script language="javascript">
        function validaCampos(){
            if(document.getElementById('nome').value == "" ||
                document.getElementById('listProdutos').value == "") {
                alert("Todos os campos são obrigatórios!");
                return false;
            }
            alert("Venda cadastrada com sucesso !");
            return true;
        }
        
        function calculaValorTotal() {
            var valorTotal = document.getElementById('valorTotal');
            valorTotal.value = 0;
            var list = document.getElementById('listProdutos');
            var i;
            for (i = 0; i < list.length; i=i+1) {
                if (list[i].selected) {
                    var valor = list[i].value.substring(list[i].value.indexOf(":", 0)+1, list[i].value.length);
                    valorTotal.value = parseFloat(valorTotal.value) + parseFloat(valor); 
                }
            }
        }
    </script>
</body>
</html>
