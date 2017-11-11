package com.crud.tasks.trello.domain.badges;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Badges {
    private int votes;
    private AttachmentsByType attachmentsByType;
}
