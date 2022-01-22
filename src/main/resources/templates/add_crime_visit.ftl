<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <h3>Create Crime visit for Crime ${crimeId}</h3>
        <form method="post" action="/crime_visit/${crimeId?c}/add">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Destruction:</label>
                <div class="col-sm-6">
                    <input type="text" name="severityDestruction"
                           class="form-control ${(severityDestructionError??)?string('is-invalid', '')}"/>
                    <#if severityDestructionError??>
                        <div class="invalid-feedback">
                            ${severityDestructionError}
                        </div>
                    </#if>
                </div>
            </div>
            <div><b>Choose Crime Scene</b></div>
            <div class="form-check">
                <#list crime_scenes as scene>
                    <div>
                        <input class="form-check-input" type="checkbox" value="${scene.getId()?c}"
                               id="${scene.getId()}" name="crimeSceneId">
                        <label class="form-check-label" for="${scene.getId()}" style="word-break: break-all">
                            ${scene.getName()} ${scene.getPlace()}
                        </label>
                    </div>
                </#list>
            </div>
            <button class="btn btn-dark" type="submit">
                Save
            </button>
        </form>
    </#if>
</@c.page>