<#import "parts/common.ftl" as cl>
<#include "parts/security.ftl">

<@cl.page>
    <#if profile??>
        <h1>Crime ${crime.getId()}</h1>
        <div>Date: ${crime.getContention().getDateContention().toLocalDate()}</div>
        <div class="alert alert-<#if crime.getCrimeStatus().name() == 'ACTIVE'>warning<#elseif crime.getCrimeStatus().name() == 'POSTPON'>info<#else>success</#if>"
             role="alert">
            ${crime.getCrimeStatus()}
        </div>
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
        <#if isSheriff && crime.getCrimeStatus() != 'CLOSED'>
            <form action="/crime/${crime.getId()}/close" method="post">
                <button type="submit" class="btn btn-dark">
                   Close Crime
                </button>
            </form>
        </#if>
        <#if crime.getDescription()??>
            <p style="word-break: break-all">
                <b>Description: ${crime.getDescription()}</b>
            </p>
        </#if>
        <h3 style="word-break: break-all">Sheriff: ${crime.getSheriff().getName()} ${crime.getSheriff().getSurname()}</h3>
        <h3>Fee: ${crime.getFee()}$</h3>
        <hr>
        <h3>Contention</h3>
        <div>
            <b>
                Description:
            </b>
        </div>
        <p style="word-break: break-all">
            ${crime.getContention().getDescription()}
        </p>
        <div>
            <b>
                Damage:
            </b>
        </div>
        <div class="progress">
            <div class="progress-bar bg-<#if damage == 'small'>success<#elseif damage == 'middle'>warning<#else>danger</#if>"
                 role="progressbar" style="width: ${crime.getContention().getDamageCritically() * 100 / 10}%"
                 aria-valuenow="${crime.getContention().getDamageCritically() * 100 / 10}"
                 aria-valuemin="0" aria-valuemax="10">
                ${crime.getContention().getDamageCritically()}
            </div>
        </div>
        <hr>
        <h3>Victim</h3>
        <div style="word-break: break-all">
            Full name: ${crime.getContention().getCharacter().getName()} ${crime.getContention().getCharacter().getSurname()}
        </div>
        <div>Age: ${crime.getContention().getCharacter().getAge()}</div>
        <div>Sex: ${crime.getContention().getCharacter().getSex()}</div>
        <div>Living place:
            <ul class="list-group" style="word-break: break-all">
                <#list victim_homes as home>
                    <li class="list-group-item">
                        <b class="text-wrap text-break">${home.getCity()}, ${home.getAvenue()}
                            avenue, ${home.getHouseNumber()}</b>
                    </li>
                </#list>
            </ul>

        </div>
        <hr>
        <h3>Criminal cases:</h3>
        <form action="/criminal_case/${crime.getId()?c}/add" method="get">
            <button type="submit" class="btn btn-dark" <#if crime.getCrimeStatus().name() == 'CLOSED'>disabled</#if>>
                Add Criminal case
            </button>
        </form>
        <div class="list-group">
            <#list crime.getCriminalCases() as cc>
                <a href="/criminal_case/${cc.getId()?c}" class="list-group-item list-group-item-action">Criminal case ${cc.getId()}</a>
            </#list>
        </div>
        <hr>
        <h3>Crime Visits</h3>
        <form action="/crime_visit/${crime.getId()?c}/add" method="get">
            <button type="submit" class="btn btn-dark" <#if crime.getCrimeStatus().name() == 'CLOSED'>disabled</#if>>
                Add Crime visit
            </button>
        </form>
        <div class="list-group">
            <#list crime.getCrimeVisits() as cv>
                <a href="/crime_visit/${cv.getId()?c}" class="list-group-item list-group-item-action">Crime visit ${cv.getVisitNumber()}</a>
            </#list>
        </div>
    </#if>
</@cl.page>