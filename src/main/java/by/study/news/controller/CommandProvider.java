package by.study.news.controller;

import java.util.HashMap;

import java.util.Map;

import by.study.news.controller.impl.ChangeLocaleToEn;
import by.study.news.controller.impl.ChangeLocaleToRu;
import by.study.news.controller.impl.GoToBasePage;
import by.study.news.controller.impl.NextPage;
import by.study.news.controller.impl.PreviousPage;
import by.study.news.controller.impl.ShowPage;
import by.study.news.controller.impl.article.AddArticle;
import by.study.news.controller.impl.article.DeleteArticle;
import by.study.news.controller.impl.article.DeleteMarked;
import by.study.news.controller.impl.article.EditArticle;
import by.study.news.controller.impl.article.GoToAddArticle;
import by.study.news.controller.impl.article.GoToEditArticle;
import by.study.news.controller.impl.article.GoToViewArticle;
import by.study.news.controller.impl.user.AddUser;
import by.study.news.controller.impl.user.GoToRegistrationPage;
import by.study.news.controller.impl.user.SignIn;
import by.study.news.controller.impl.user.SignOut;

public final class CommandProvider {
	private static final CommandProvider instance = new CommandProvider();

	private Map<CommandName, Command> commands = new HashMap<>();

	private CommandProvider() {

		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());

		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.GO_TO_VIEW_ARTICLE, new GoToViewArticle());
		commands.put(CommandName.GO_TO_ADD_ARTICLE, new GoToAddArticle());
		commands.put(CommandName.GO_TO_EDIT_ARTICLE, new GoToEditArticle());

		commands.put(CommandName.ADD_ARTICLE, new AddArticle());
		commands.put(CommandName.EDIT_ARTICLE, new EditArticle());
		commands.put(CommandName.DELETE_MARKED, new DeleteMarked());
		commands.put(CommandName.DELETE_ARTICLE, new DeleteArticle());

		commands.put(CommandName.NEXT_PAGE, new NextPage());
		commands.put(CommandName.PREVIOUS_PAGE, new PreviousPage());
		commands.put(CommandName.SHOW_PAGE, new ShowPage());

		commands.put(CommandName.ADD_USER, new AddUser());
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.SIGN_OUT, new SignOut());

		commands.put(CommandName.CHANGE_LOCALE_TO_EN, new ChangeLocaleToEn());
		commands.put(CommandName.CHANGE_LOCALE_TO_RU, new ChangeLocaleToRu());
	}

	public Command getCommand(String name) {
		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		return commands.get(commandName);
	}

	public static CommandProvider getInstance() {
		return instance;
	}

}
