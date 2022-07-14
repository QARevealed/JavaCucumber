package group.revealed.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SlackChannel {
    QA_disco("qa_disco","https://hooks.slack.com/services/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"),
    QA_DEMO("tec_pmd_qa_demo", "https://hooks.slack.com/services/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");

    private final String channelName;
    private final String webhook;
}