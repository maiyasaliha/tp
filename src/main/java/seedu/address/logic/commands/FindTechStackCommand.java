package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.TechStackContainsKeywordsPredicate;

/**
 * Finds and lists all contacts in address book whose tech stack contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTechStackCommand extends Command {

    public static final String COMMAND_WORD = "find-ts";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts whose tech stacks contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " java python ";

    private final TechStackContainsKeywordsPredicate predicate;

    public FindTechStackCommand(TechStackContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindTechStackCommand otherFindTechStackCommand = (FindTechStackCommand) other;
        return predicate.equals(otherFindTechStackCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
