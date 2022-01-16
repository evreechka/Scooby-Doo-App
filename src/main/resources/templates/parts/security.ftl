<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    profile = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = profile.getUsername()
    activeId = profile.getId()
    >
<#else>
    <#assign
    name = ""
    isAdmin = false
    activeId = -1
    >
</#if>