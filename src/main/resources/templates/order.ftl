<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Order #${order.getId()}</h5>
            <div class="alert alert-<#if order.getOrderStatus() == 'NOT_CONFIRMED'>dark<#elseif order.getOrderStatus() == 'ON_WAY'>warning<#else>success</#if>" role="alert">
                ${order.getOrderStatus().name()}
            </div>

            <ul class="list-group list-group-flush">
                <li class="list-group-item"
                    style="word-break: break-all">${order.getOrderer().getCharacter().getName()} ${order.getOrderer().getCharacter().getSurname()}</li>
                <li class="list-group-item"> crimes</li>
                <li class="list-group-item">My main feature is ${feature}</li>
                <li class="list-group-item">Money: ${bank_account}$</li>
            </ul>
        </div>
        <#if activeId == profileId>
            <a class="btn btn-dark" href="/profile/${activeId}/edit" role="button">Edit</a>
        </#if>
    </div>
</@c.page>