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
                    <td>${user.getCharacter().getName()} ${user.getCharacter().getSurname()}</td>
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
                                    <i class="bi bi-x-square-fill"></i>
                                </button>
                            </form>
                        </td>
                    </#if>
                </tr>
            </#list>
        </table>
    </#if>
</@c.page>