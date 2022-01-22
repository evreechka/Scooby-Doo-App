<#import "parts/common.ftl" as cl>
<#import "parts/login.ftl" as l>

<@cl.page>
    <h5>Login</h5>
    <form action="/register" method="get">
        <button type="submit" class="btn btn-dark">
            Registration
        </button>
    </form>
    <#if success??>
        <div class="alert alert-success" role="alert">
            ${success}
        </div>
    </#if>
    <#if error??>
        <div class="alert alert-danger" role="alert">
            ${error}
        </div>
    </#if>
    <#if RequestParameters.logout??>
        <div class="alert alert-info" role="alert">
            You have been logged out.
        </div>
    </#if>
    <@l.login "/login"/>
</@cl.page>