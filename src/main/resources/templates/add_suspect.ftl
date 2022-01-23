<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <h3>Add suspect in Crime visit ${crimeVisitId}</h3>
        <form method="post" action="/suspect/${crimeVisitId?c}/add">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Motive:</label>
                    <textarea name="motive"
                              class="form-control ${(motiveError??)?string('is-invalid', '')}"></textarea>
                    <#if motiveError??>
                        <div class="invalid-feedback">
                            ${motiveError}
                        </div>
                    </#if>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Involvement:</label>
                <div class="col-sm-6">
                    <input type="text" name="involvement"
                           class="form-control ${(involvementError??)?string('is-invalid', '')}"/>
                    <#if involvementError??>
                        <div class="invalid-feedback">
                            ${involvementError}
                        </div>
                    </#if>
                </div>
            </div>
            <div><b>Сharacter data:</b></div>
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
                <label class="col-sm-2 col-form-label">Surname:</label>
                <div class="col-sm-6">
                    <input type="text" name="surname"
                           class="form-control ${(surnameError??)?string('is-invalid', '')}"/>
                    <#if surnameError??>
                        <div class="invalid-feedback">
                            ${surnameError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Age:</label>
                <div class="col-sm-6">
                    <input type="text" name="age"
                           class="form-control ${(ageError??)?string('is-invalid', '')}"/>
                    <#if ageError??>
                        <div class="invalid-feedback">
                            ${ageError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Sex</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="sex">
                    <option value="FEMALE" selected>FEMALE</option>
                    <option value="MALE">MALE</option>
                    <option value="OTHER">OTHER</option>
                </select>
            </div>
            <button class="btn btn-dark" type="submit">
                Save
            </button>
        </form>
    </#if>
</@c.page>