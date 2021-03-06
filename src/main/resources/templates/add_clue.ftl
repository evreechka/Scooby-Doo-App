<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <h3>Add Clue to Criminal case ${criminalCaseId}</h3>
        <form method="post" action="/clue/${criminalCaseId?c}/add">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Name:</label>
                <div class="col-sm-6">
                    <input type="text" name="name"
                           class="form-control ${(nameError??)?string('is-invalid', '')}"/>
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Description:</label>
                <input name="description" class="form-control ${(descriptionError??)?string('is-invalid', '')}"></input>
                <#if descriptionError??>
                    <div class="invalid-feedback">
                        ${descriptionError}
                    </div>
                </#if>
            </div>
            <div><b>Choose Crime visits</b></div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Crime Visits</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="crimeVisitId">
                    <#list crimeVisits as cv>
                        <option value="${cv.getId()?c}" selected>Crime visit ${cv.getVisitNumber()}</option>
                    </#list>
                </select>
            </div>
            <button class="btn btn-dark" type="submit">
                Save
            </button>
        </form>
    </#if>
</@c.page>