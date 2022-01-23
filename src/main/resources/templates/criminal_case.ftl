<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <#if profile??>
        <h1>Criminal case ${criminal_case.getId()}</h1>
        <#if isAdmin && criminal_case.getQuilt()>
            <form action="/criminal_case/${criminal_case.getId()?c}/close">
                <button type="submit" class="btn btn-dark">
                    Close Criminal case
                </button>
            </form>
        </#if>
        <div>
            <b>
                Severity:
            </b>
        </div>
        <div class="progress">
            <div class="progress-bar bg-<#if severity == 'nothing'>success<#elseif severity == 'small'>info<#elseif severity == 'middle'>warning<#else>danger</#if>"
                 role="progressbar" style="width: ${criminal_case.getSeverity() * 100 / 10}%"
                 aria-valuenow="${criminal_case.getSeverity() * 100 / 10}"
                 aria-valuemin="0" aria-valuemax="10">
                ${criminal_case.getSeverity()}
            </div>
        </div>
        <#if criminal_case.getQuilt()??>
            <h3>Quilt</h3>
            <div style="word-break: break-all">
                <b>Quilt:</b> ${criminal_case.getQuilt().getCharacter().getName()} ${criminal_case.getQuilt().getCharacter().getSurname()}
            </div>
            <div style="word-break: break-all">
                <b>Motive:</b> ${criminal_case.getQuilt().getMotive()}
            </div>
            <hr>
        </#if>
        <h3>Monster</h3>
        <div style="word-break: break-all">
            <b>Name: </b>${criminal_case.getMonster().getMonsterName()}
        </div>
        <#if criminal_case.getMonster().getMonsterDescription()??>
            <div style="word-break: break-all">
                <b>Description: </b>${criminal_case.getMonster().getMonsterDescription()}
            </div>
        </#if>
        <div>
            <b>Weight: </b>${criminal_case.getMonster().getWeight()}
        </div>
        <div>
            <b>Height: </b>${criminal_case.getMonster().getHeight()}
        </div>
        <div>
            <b>Type: </b>${criminal_case.getMonster().getMonsterType().getName()}
        </div>
        <div>
            <b>Feature: </b>${criminal_case.getMonster().getMonsterType().getMonsterFeature().name()}
        </div>
        <#if criminal_case.getMonster().getInternetInformation()??>
            <div style="word-break: break-all">
                <b>InternetInformation: </b>${criminal_case.getMonster().getInternetInformation()}
            </div>
        </#if>
        <hr>
        <h3>Orders</h3>
        <div class="list-group">
            <#list criminal_case.getOrders() as order>
                <a href="/order/${order.getId()?c}" class="list-group-item list-group-item-action">Order #${order.getId()}</a>
            </#list>
        </div>
        <hr>
<#--        /order/inventory/{investigator_id}/{criminal_case_id}-->
        <h3>Traps</h3>
        <form action="/order/inventory/${criminal_case.getId()?c}">
            <button type="submit" class="btn btn-light">
                Create trap
            </button>
        </form>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Usefulness</th>
            </tr>
            </thead>
            <tbody>
            <#list criminal_case.getTrapCases() as trap>
                <tr>
                    <td style="word-break: break-all">${trap.getName()}</td>
                    <td style="word-break: break-all">${trap.getUsefulness()}</td>
                </tr>
            </#list>
            </tbody>
        </table>
        <hr>
        <h3>Clues</h3>
        <form action="/clue/${criminal_case.getId()?c}/add">
            <button type="submit" class="btn btn-light">
                Add clue
            </button>
        </form>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Add suspects</th>
            </tr>
            </thead>
            <tbody>
            <#list criminal_case.getClues() as clue>
                <tr>
                    <td style="word-break: break-all">${clue.getName()}</td>
                    <td style="word-break: break-all">${clue.getDescription()}</td>
                    <td>
                        <form action="/clue/${criminal_case.getId()?c}/${clue.getId()?c}/add_suspects">
                            <button type="submit" class="btn btn-light">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-clipboard-plus" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M8 7a.5.5 0 0 1 .5.5V9H10a.5.5 0 0 1 0 1H8.5v1.5a.5.5 0 0 1-1 0V10H6a.5.5 0 0 1 0-1h1.5V7.5A.5.5 0 0 1 8 7z"/>
                                    <path d="M4 1.5H3a2 2 0 0 0-2 2V14a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V3.5a2 2 0 0 0-2-2h-1v1h1a1 1 0 0 1 1 1V14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V3.5a1 1 0 0 1 1-1h1v-1z"/>
                                    <path d="M9.5 1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5h3zm-3-1A1.5 1.5 0 0 0 5 1.5v1A1.5 1.5 0 0 0 6.5 4h3A1.5 1.5 0 0 0 11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3z"/>
                                </svg>
                            </button>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#if>
</@c.page>