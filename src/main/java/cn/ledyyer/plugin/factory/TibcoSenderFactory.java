package cn.ledyyer.plugin.factory;

import cn.ledyyer.plugin.ui.TibcoSenderUI;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class TibcoSenderFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        TibcoSenderUI tibcoSenderUI = new TibcoSenderUI();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(tibcoSenderUI.getRoot(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
