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
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Crime Scenes</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="crimeSceneId">
                    <#list crime_scenes as scene>
                        <option value="${scene.getId()?c}" selected>${scene.getName()} ${scene.getPlace()}</option>
                    </#list>
                </select>
            </div>
            <button class="btn btn-dark" type="submit">
                Save
            </button>
        </form>
    </#if>
</@c.page>