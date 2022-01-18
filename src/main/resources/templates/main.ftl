<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <#if profile??>
        <p>Hello, ${profile.getUsername()}!</p>
        <div class="nav-item">
            <a class="nav-link" href="/profile/${activeId}">See profile</a>
        </div>
    </#if>
</@c.page>