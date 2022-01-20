<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <form method="post" action="/contention/add">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Description:</label>
                <textarea type="text" name="description"
                          class="form-control ${(descriptionError??)?string('is-invalid', '')}"></textarea>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Damage Critically:</label>
                <div class="col-sm-6">
                    <input type="text" name="damageCritically"
                           class="form-control ${(damageCriticallyError??)?string('is-invalid', '')}"/>
                    <#if damageCriticallyError??>
                        <div class="invalid-feedback">
                            ${damageCriticallyError}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Character Id:</label>
                <div class="col-sm-6">
                    <input type="text" name="characterId"
                           class="form-control ${(idError??)?string('is-invalid', '')}"/>
                    <#if idError??>
                        <div class="invalid-feedback">
                            ${idError}
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