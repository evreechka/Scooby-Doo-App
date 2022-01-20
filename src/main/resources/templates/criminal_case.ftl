<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <#if profile??>
        <h1>Criminal case ${criminal_case.getId()}</h1>
        <#if isAdmin && criminal_case.getQuilt()>
            <form action="/criminal_case/${criminal_case.getId()}/close">
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
                 role="progressbar" style="width: ${crime.getContention().getDamageCritically() * 100 / 10}%"
                 aria-valuenow="${criminal_case.getSeverity() * 100 / 10}"
                 aria-valuemin="0" aria-valuemax="10">
                ${criminal_case.getSeverity()}
            </div>
        </div>
        <#if criminal_case.getQuilt()??>
            <h3>Quilt</h3>
            <div style="word-break: break-all">
                <b>Quilt:</b> ${criminal_case.getQuilt().getCharacterId().getName()} ${criminal_case.getQuilt().getCharacterId().getSurname()}
            </div>
            <div style="word-break: break-all">
                <b>Motive:</b> ${criminal_case.getQuilt().getMotive()}
            </div>
            <hr>
        </#if>
        <h3>Monster</h3>
        <div style="word-break: break-all">
            <b>Name: </b>${criminal_case.getMonster().getName()}
        </div>
        <#if criminal_case.getMonster().getDescription()??>
            <div style="word-break: break-all">
                <b>Description: </b>${criminal_case.getMonster().getDescription()}
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
                <a href="/order/${order.getId()}" class="list-group-item list-group-item-action">Order #${order.getId()}</a>
            </#list>
        </div>
        <hr>
        <h3>Clues</h3>
        <form action="/clue/${criminal_case.getId()}/add">
            <button type="submit" class="btn btn-dark" <#if criminal_case.getQuilt()??>disabled</#if>>
                Add Clue
            </button>
        </form>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
            </tr>
            </thead>
            <tbody>
            <#list criminal_case.getClues() as clue>
                <tr>
                    <td style="word-break: break-all">${clue.getName()}</td>
                    <td style="word-break: break-all">${clue.getDescription()}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#if>
</@c.page>