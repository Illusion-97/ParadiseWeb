<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:if test="${side == 'Trip'}">
    <div class="Tripmain">
        <div class="TripformLike">
            <input class="Tripname" type="text" id="${bean.id}" placeholder="Name" value="${bean.name}"/>
            <select class="departure">
                <option value="">Departure</option>
                <c:forEach items="${listP}" var="o">
                    <c:choose>
                        <c:when test="${o.id == bean.departure.id}">
                            <option value="${o.id}" selected>
                                <c:out value="${o.name}"/>
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="${o.id}">
                                <c:out value="${o.name}"/>
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <select class="terminal">
                <option value="">Terminal</option>
                <c:forEach items="${listP}" var="o">
                    <c:choose>
                        <c:when test="${o.id == bean.terminus.id}">
                            <option value="${o.id}" selected>
                                <c:out value="${o.name}"/>
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="${o.id}">
                                <c:out value="${o.name}"/>
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <input class = "cost" type="text" name="currency-field" id="currency-field" pattern="^\$\d{1,3}(,\d{3})*(\.\d+)?$" value="${bean.cost}" data-type="currency" placeholder="Trip Cost: (Ex:123.45)">
        </div>
        <div class="TripbuttonGrid">
            <div class="TriptabTitle" onclick="create(this)">
                <div class="Triptitle">Create</div>
            </div>
            <div class="TriptabTitle" onclick="remove()">
                <div class="Triptitle">Remove</div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${side == 'Place'}">
    <div class="Placemain">
        <div class="PlaceformLike">
            <input class="Placename" type="text" id="${bean.id}" placeholder="Name" value="${bean.name}"/>
            <div class="PlacetabElement" onclick="edit()">
                <div class="Placetitle">Edit</div>
            </div>
        </div>
        <div class="PlacebuttonGrid">
            <div class="PlacetabTitle" onclick="create(this)">
                <div class="Placetitle">Create</div>
            </div>
            <div class="PlacetabTitle" onclick="remove()">
                <div class="Placetitle">Remove</div>
            </div>
        </div>
    </div>
</c:if>
