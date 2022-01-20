<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <#if profile??>
        <h1>Crime visit ${crime_visit.getVisitNumber()}</h1>
        <h3>Command of Investigators</h3>
        <#list crime_visit.getParticipants() as inv>
            <div style="word-break: break-all">
                <b>${inv.getInvestigator().getCharacter().getName()} ${inv.getInvestigator().getCharacter().getSurname()}
                    : </b>${inv.getVisitRole().name()}
            </div>
        </#list>
        <hr>
        <h3>Crime Scene</h3>
        <div style="word-break: break-all">
            <b>Name: </b>${crime_visit.getCrimeScene().getName()}
        </div>
        <div>
            <b>Place type: </b>${crime_visit.getCrimeScene().getPlace().name()}
        </div>
        <div style="word-break: break-all">
            <b>Address: </b>${crime_visit.getCrimeScene().getAddress().getCity()}
            , ${crime_visit.getCrimeScene().getAddress().getAvenue()}
            avenue, ${crime_visit.getCrimeScene().getAddress().getHouseNumber()}
        </div>
        <hr>
        <h3>Victims</h3>
        <form action="/victim/${crime_visit.getId()?c}/add">
            <button type="submit" class="btn btn-dark"
                    <#if crime_visit.getCrime().getCrimeStatus() == 'CLOSED'>disabled</#if>>
                Add Victim
            </button>
        </form>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Full name</th>
                <th scope="col">Date</th>
                <th scope="col">Indication</th>
            </tr>
            </thead>
            <tbody>
            <#list crime_visit.getVictims() as victim>
                <tr>
                    <td style="word-break: break-all">${victim.getCharacter().getName()} ${victim.getCharacter().getSurname()}</td>
                    <td>${victim.getDateIndication()}</td>
                    <td style="word-break: break-all">${victim.getIndication()}</td>
                </tr>
            </#list>
            </tbody>
        </table>
        <hr>
        <h3>Suspects</h3>
        <form action="/suspect/${crime_visit.getId()?c}/add">
            <button type="submit" class="btn btn-dark"
                    <#if crime_visit.getCrime().getCrimeStatus() == 'CLOSED'>disabled</#if>>
                Add Suspect
            </button>
        </form>
        <ul class="list-group">
            <#list crime_visit.getSuspects() as suspect>
                <li class="list-group-item" style="word-break: break-all">${suspect.getCharacterId().getName()} ${suspect.getCharacterId().getSurname()}</li>
            </#list>
        </ul>
    </#if>
</@c.page>