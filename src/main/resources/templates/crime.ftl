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
        <#if isSheriff && crime.getCrimeStatus() != 'CLOSED'>
            <form action="/clue/${cc.getId()}" method="get">
                <button type="submit" class="btn btn-dark">
                    See clues
                </button>
            </form>
        </#if>
        <#if crime.getDescription()??>
            <p>
                <b>Description: ${crime.getDescription()}</b>
            </p>
        </#if>
        <h3>Sheriff: ${crime.getSheriff().getName()} ${crime.getSheriff().getSurname()}</h3>
        <h3>Fee: ${crime.getFee()}$</h3>
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
        <h3>Victim</h3>
        <div style="word-break: break-all">
            Full
            name: ${crime.getContention().getCharacter().getName()} ${crime.getContention().getCharacter().getSurname()}
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
        <h3>Criminal cases:</h3>
        <div class="card-group">
            <#list crime.getCriminalCases() as cc>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Criminal case ${cc.getId()}</h5>
                        <div class="card-text" style="word-break: break-all">
                            Monster: ${cc.getMonster().getName()}
                            <form action="/monster/${cc.getMonster().getId()}" method="get">
                                <button type="submit" class="btn btn-primary">
                                    See Monster Information
                                </button>
                            </form>
                        </div>
                        <#if cc.getPunishment()??>
                            <p class="card-text">Punishment: ${cc.getPunishment()}</p>
                        </#if>
                        <#if cc.getQuilt()??>
                            <p class="card-text" style="word-break: break-all">
                                <b>Quilt: ${cc.getQuilt().getCharacterId().getName()} ${cc.getQuilt().getCharacterId().getSurname()}</b>
                            </p>
                            <p class="card-text text-wrap text-break">Motive: ${cc.getQuilt().getMotive()}</p>
                        </#if>
                        <p class="card-text">Severity: ${cc.getSeverity() * 100 / 10}%</p>
                        <form action="/criminal_case/${cc.getId()}" method="get">
                            <button type="submit" class="btn btn-dark">
                                More information
                            </button>
                        </form>
                    </div>
                </div>
            </#list>
        </div>
        <h3>Crime Visits</h3>
        <table class="table table-sm align-middle">
            <thead>
            <tr>
                <th scope="col">Visit number</th>
                <th scope="col">Destruction</th>
                <th scope="col">Date</th>
                <th scope="col">CrimeScene</th>
                <th scope="col">Address</th>
            </tr>
            </thead>
            <tbody>
            <#list crime.getCrimeVisits() as visit>
                <tr class="align-top">
                    <th scope="row">${visit.getVisitNumber()}</th>
                    <td>${visit.getSeverityDestruction() * 100 / 10}%</td>
                    <td>${visit.getDateVisit()}</td>
                    <td style="word-break: break-all">${visit.getCrimeScene().getName()}
                        , ${visit.getCrimeScene().getPlace()}</td>
                    <td style="word-break: break-all">${visit.getCrimeScene().getAddress().getCity()}
                        , ${visit.getCrimeScene().getAddress().getAvenue()}
                        avenue, ${visit.getCrimeScene().getAddress().getHouseNumber()}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#if>
</@cl.page>