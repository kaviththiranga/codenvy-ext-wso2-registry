package org.wso2.developerstudio.codenvy.ext.registry.client.wizard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by kavith on 7/16/14.
 */
public class MavenSettingsPageViewImpl implements MavenSettingsPageView{

    private static MavenSettingsPageViewImplUiBinder uiBinder = GWT.create(MavenSettingsPageViewImplUiBinder.class);

    private final DockLayoutPanel rootElement;

    @UiField
    TextBox versionField;
    @UiField
    TextBox groupId;
    @UiField
    TextBox artifactId;
    @UiField
    ListBox packagingField;

    interface MavenSettingsPageViewImplUiBinder extends UiBinder<DockLayoutPanel, MavenSettingsPageViewImpl>{}

    public MavenSettingsPageViewImpl() {

        rootElement = uiBinder.createAndBindUi(this);
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {

    }

    @Override
    public Widget asWidget() {
        return rootElement;
    }

    @Override
    public void setArtifactId(String artifact) {

    }

    @Override
    public void setGroupId(String group) {

    }

    @Override
    public void setVersion(String value) {

    }

    @Override
    public String getPackaging() {
        return null;
    }

    @Override
    public String getGroupId() {
        return null;
    }

    @Override
    public String getArtifactId() {
        return null;
    }

    @Override
    public String getVersion() {
        return null;
    }
}
