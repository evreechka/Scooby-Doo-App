<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    profile = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    activeId = profile.getId()
    isAdmin = profile.isAdmin()
    isSheriff = profile.isSheriff()
    >
<#else>
    <#assign
    isAdmin = false
    isSheriff = false
    activeId = -1
    >
</#if>