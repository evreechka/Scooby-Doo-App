<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <#if profile??>
            <form action="/crime_scene/add" method="get">
                <button class="btn btn-dark">Generate Crime Scene</button>
            </form>

            <div class="card">
                <#if photo??>
                    <img class="card-img-top" alt="Profile photo" src="/img/${photo}">
                <#else>
                    <img src="/img/avatar.png" class="card-img-top" alt="Profile photo">
                </#if>
                <div class="card-body">
                    <h5 class="card-title">${name} ${surname}</h5>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">${age} years of old</li>
                       <#if isInvestigator || isAdmin><li class="list-group-item">I've taken part in ${crime_count} crimes</li></#if>
                       <#if isInvestigator || isAdmin><li class="list-group-item">My main feature is ${feature}</li></#if>
                        <#if isInvestigator || isAdmin><li class="list-group-item">Money: ${bank_account}$</li></#if>
                    </ul>
                </div>
                <#if activeId == profileId>
                    <a class="btn btn-dark" href="/profile/${activeId}/edit" role="button">Edit</a>
                </#if>
            </div>
        </#if>
</@c.page>