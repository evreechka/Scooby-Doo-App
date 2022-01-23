<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    profile = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    activeId = profile.getId()
    isAdmin = profile.isAdmin()
    isSheriff = profile.isSheriff()
    isUser = profile.isUser()
    isInvestigator = profile.isInvestigator()
    >
<#else>
    <#assign
    isAdmin = false
    isSheriff = false
    isUser = false
    isInvestigator = false
    activeId = -1
    >
</#if>