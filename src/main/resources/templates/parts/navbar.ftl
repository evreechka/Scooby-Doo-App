<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Scooby-Doo</a>
    <#if profile??>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </#if>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if profile??>
                <li class="nav-item">
                    <a class="nav-link" href="/">Crimes</a>
                </li>
            </#if>
            <#if profile??>
                <li class="nav-item">
                    <a class="nav-link" href="/profile/${activeId}">Profile</a>
                </li>
            </#if>
            <#if profile??>
                <li class="nav-item">
                    <a class="nav-link" href="/investigator">Investigators</a>
                </li>
            </#if>
            <#if profile??>
                <li class="nav-item">
                    <a class="nav-link" href="/monster">Monsters</a>
                </li>
            </#if>
            <#if profile?? && (isAdmin || isInvestigator)>
                <li class="nav-item">
                    <a class="nav-link" href="/inventory">Inventory</a>
                </li>
            </#if>
            <#if profile?? && (isAdmin || isInvestigator)>
                <li class="nav-item">
                    <a class="nav-link" href="/trap">Traps</a>
                </li>
            </#if>
            <#if profile?? && !isUser>
                <li class="nav-item">
                    <a class="nav-link" href="/character">Characters</a>
                </li>
            </#if>
        </ul>
        <#if profile??>
            <@l.logout/>
        </#if>
    </div>
</nav>