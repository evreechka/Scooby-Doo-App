<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
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
                <textarea name="description" class="form-control ${(descriptionError??)?string('is-invalid', '')}"></textarea>
                <#if descriptionError??>
                    <div class="invalid-feedback">
                        ${descriptionError}
                    </div>
                </#if>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Crime visit number:</label>
                <div class="col-sm-6">
                    <input type="text" name="visitNumber"
                           class="form-control ${(visitError??)?string('is-invalid', '')}"/>
                    <#if visitError??>
                        <div class="invalid-feedback">
                            ${visitError}
                        </div>
                    </#if>
                </div>
            </div>
            <button class="btn btn-dark" type="submit">
                Save
            </button>
        </form>
    </#if>
</@c.page>