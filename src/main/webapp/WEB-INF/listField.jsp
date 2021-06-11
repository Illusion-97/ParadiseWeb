<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="tabContainer">
    <div class="tabTitleGrid">
        <div class="tabTitle" id="Trip" onclick=updateList(this)>
            <div class="title">
                Trips
            </div>
        </div>
        <div class="tabTitle" id="Place" onclick=updateList(this)>
            <div class="title">
                Places
            </div>
        </div>
    </div>
    <div class="tabContentList" id="${side}" >
        <c:forEach items="${list}" var="o">
            <div class="tabElement" id="${o.id}" onclick=getForm(this)>
                <div class="title">
                    <c:out value="${o.name}"/>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
