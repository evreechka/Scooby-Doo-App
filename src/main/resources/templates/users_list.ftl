<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <#if profile??>
        <h3>Investigators</h3>
        <#if isAdmin>
            <td>
                <form action="/profile/add" method="get">
                    <button type="submit" class="btn btn-dark">
                        Add new user
                    </button>
                </form>
            </td>
        </#if>
        <table class="table table-striped">
            <#list users as user>
                <tr>
                    <td>${user.getCharacter().getId()}</td>
                    <td style="word-break: break-all">${user.getCharacter().getName()} ${user.getCharacter().getSurname()}</td>
                    <td>${user.getCharacter().getSex().name()}</td>
                    <td><form action="/profile/${user.getProfile().getId()}" method="get">
                            <button type="submit" class="btn btn-secondary">
                                See profile
                            </button>
                        </form>
                    </td>
                    <#if isAdmin>
                        <td>
                            <form action="/profile/${user.getProfile().getId()}/delete" method="post">
                                <button type="submit" class="btn btn-light">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16">
                                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                        <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                    </svg>
                                </button>
                            </form>
                        </td>
                    </#if>
                </tr>
            </#list>
        </table>
    </#if>
</@c.page>