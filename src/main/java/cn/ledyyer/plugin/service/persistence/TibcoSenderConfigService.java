package cn.ledyyer.plugin.service.persistence;

import cn.ledyyer.plugin.bean.TibcoSenderConfig;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.ProjectManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "tibcoSenderConfig", storages = {@Storage(value = "tibcoSenderConfig.xml")})
public class TibcoSenderConfigService implements PersistentStateComponent<TibcoSenderConfig> {
    private TibcoSenderConfig tibcoSenderConfig = null;

    public static TibcoSenderConfigService getInstance(){
        return ProjectManager.getInstance().getDefaultProject().getService(TibcoSenderConfigService.class);
    }

    @Override
    public @Nullable TibcoSenderConfig getState() {
        if (tibcoSenderConfig==null){
            tibcoSenderConfig = new TibcoSenderConfig();
        }
        return tibcoSenderConfig;
    }

    @Override
    public void loadState(@NotNull TibcoSenderConfig state) {
        tibcoSenderConfig = state;
    }
}
