package com.pixelscientists.holoskin;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HoloSkin extends ApplicationAdapter {
    Object[] listEntries = {"This is a list entry1", "And another one1", "The meaning of life1", "Is hard to come by1",
            "This is a list entry2", "And another one2", "The meaning of life2", "Is hard to come by2", "This is a list entry3",
            "And another one3", "The meaning of life3", "Is hard to come by3", "This is a list entry4", "And another one4",
            "The meaning of life4", "Is hard to come by4", "This is a list entry5", "And another one5", "The meaning of life5",
            "Is hard to come by5"};

    Skin skin;
    Stage stage;
    Texture texture1;
    Texture texture2;
    Label fpsLabel;

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        texture1 = new Texture(Gdx.files.internal("data/smallandroid.png"));
        texture2 = new Texture(Gdx.files.internal("data/bigandroid.png"));
        TextureRegion image = new TextureRegion(texture1);
        TextureRegion imageFlipped = new TextureRegion(image);
        imageFlipped.flip(true, true);
        TextureRegion image2 = new TextureRegion(texture2);
        // stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, new PolygonSpriteBatch());
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Group.debug = true;

        ImageButtonStyle style = new ImageButtonStyle(skin.get(ButtonStyle.class));
        style.imageUp = new TextureRegionDrawable(image);
        style.imageDown = new TextureRegionDrawable(imageFlipped);
        final ImageButton iconButton = new ImageButton(style);

        final Button buttonMulti = new TextButton("Multi\nLine\nToggle", skin, "toggle");
        final Button imgButton = new Button(new Image(image), skin, "colored");
        final Button imgToggleButton = new Button(new Image(image), skin, "toggle");

        Label myLabel = new Label("this is some text.", skin);
        myLabel.setWrap(true);

        Table t = new Table();
        t.row();
        t.add(myLabel);

        t.layout();

        final CheckBox checkBox = new CheckBox(" Continuous rendering", skin);
        checkBox.setChecked(true);
        final Slider slider = new Slider(0, 10, 1, false, skin);
        slider.setAnimateDuration(0.3f);
        final CheckBox radioButton = new CheckBox(" Disable everything", skin, "radio");
        final ProgressBar progressBar = new ProgressBar(0, 10, 1, false, skin);
        progressBar.setValue(1f);
        progressBar.setAnimateDuration(0.3f);
        final TextField textfield = new TextField("", skin);
        textfield.setMessageText("Click here!");
        textfield.setAlignment(Align.right);
        final SelectBox selectBox = new SelectBox(skin);
        selectBox.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println(selectBox.getSelected());
            }
        });
        selectBox.setItems("Android1", "Windows1 long text in item", "Linux1", "OSX1", "Android2", "Windows2", "Linux2", "OSX2",
                "Android3", "Windows3", "Linux3", "OSX3", "Android4", "Windows4", "Linux4", "OSX4", "Android5", "Windows5", "Linux5",
                "OSX5", "Android6", "Windows6", "Linux6", "OSX6", "Android7", "Windows7", "Linux7", "OSX7");
        selectBox.setSelected("Linux6");
        Image imageActor = new Image(image2);
        ScrollPane scrollPane = new ScrollPane(imageActor);
        List list = new List(skin);
        list.setItems(listEntries);
        list.getSelection().setMultiple(true);
        list.getSelection().setRequired(false);
        // list.getSelection().setToggle(true);
        ScrollPane scrollPane2 = new ScrollPane(list, skin);
        scrollPane2.setFlickScroll(false);
        final SplitPane splitPane = new SplitPane(scrollPane, scrollPane2, false, skin, "default-horizontal");
        fpsLabel = new Label("fps:", skin);

        // configures an example of a TextField in password mode.
        final Label passwordLabel = new Label("Textfield in password mode: ", skin);
        final TextField passwordTextField = new TextField("", skin);
        passwordTextField.setMessageText("password");
        passwordTextField.setPasswordCharacter('*');
        passwordTextField.setPasswordMode(true);

        final Slider verticalSlider = new Slider(0, 10, 1, true, skin);
        verticalSlider.setAnimateDuration(0.3f);
        final ProgressBar verticalProgressBar = new ProgressBar(0, 10, 1, true, skin);
        verticalProgressBar.setValue(1f);
        verticalProgressBar.setAnimateDuration(0.3f);

        final Label textAreaLabel = new Label("Textarea with multiple lines: ", skin);
        final TextArea textArea = new TextArea("Some text\nSome more text\n\nAnd the final text after a paragraph!", skin, "area");

        buttonMulti.addListener(new TextTooltip("This is a tooltip! This is a tooltip! This is a tooltip! This is a tooltip! This is a tooltip! This is a tooltip!", skin));
        Table tooltipTable = new Table(skin);
        tooltipTable.pad(10).background("tooltip");
        tooltipTable.add(new TextButton("Fancy tooltip!", skin));
        imgButton.addListener(new Tooltip(tooltipTable));

        Touchpad touchpad = new Touchpad(0f, skin);

        Tree tree = new Tree(skin);
        Tree.Node node1 = new Tree.Node(new Label("Node 1", skin));
        Tree.Node node11 = new Tree.Node(new Label("Node 1.1", skin));
        Tree.Node node12 = new Tree.Node(new Label("Node 1.2", skin));
        Tree.Node node13 = new Tree.Node(new Label("Node 1.3", skin));
        node1.add(node11);
        node1.add(node12);
        node1.add(node13);
        Tree.Node node2 = new Tree.Node(new Label("Node 2", skin));
        Tree.Node node3 = new Tree.Node(new Label("Node 3", skin));
        tree.add(node1);
        tree.add(node2);
        tree.add(node3);

        radioButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                iconButton.setDisabled(!iconButton.isDisabled());
                buttonMulti.setDisabled(!buttonMulti.isDisabled());
                imgButton.setDisabled(!imgButton.isDisabled());
                imgToggleButton.setDisabled(!imgToggleButton.isDisabled());
                checkBox.setDisabled(!checkBox.isDisabled());
                slider.setDisabled(!slider.isDisabled());
                radioButton.setDisabled(!radioButton.isDisabled());
                progressBar.setDisabled(!progressBar.isDisabled());
                textfield.setDisabled(!textfield.isDisabled());
                selectBox.setDisabled(!selectBox.isDisabled());
                passwordTextField.setDisabled(!passwordTextField.isDisabled());
                textArea.setDisabled(!textArea.isDisabled());
                Timer.instance().scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        radioButton.setDisabled(false);
                    }
                }, 10);
            }
        });

        // window.debug();
        Window window = new Window("Dialog", skin);
        window.getTitleTable().add(new TextButton("X", skin));
        window.setPosition(0, 0);
        window.defaults().spaceBottom(10);
        window.row().fill().expandX();
        window.add(iconButton);
        window.add(buttonMulti);
        window.add(imgButton);
        window.add(imgToggleButton);
        window.row();
        window.add(checkBox);
        window.add(slider).minWidth(100).fillX().colspan(3);
        window.row();
        window.add(radioButton);
        window.add(progressBar).minWidth(100).fillX().colspan(3);
        window.row();
        window.add(selectBox).fillX();
        window.add(textfield).minWidth(100).expandX().fillX().colspan(3);
        window.row();
        window.add(splitPane).fill().expand().colspan(4).maxHeight(200);
        window.row();
        window.add(passwordLabel).colspan(2);
        window.add(passwordTextField).minWidth(100).expandX().fillX().colspan(2);
        window.row();
        window.add(passwordLabel).colspan(2);
        window.add(passwordTextField).minWidth(100).expandX().fillX().colspan(2);
        window.row();
        window.add(verticalSlider);
        window.add(verticalProgressBar);
        window.add(textArea).minWidth(300).minHeight(200).expandX().fillX().colspan(2);
        window.row();
//        window.add(touchpad).colspan(2).fill();
//        window.add(tree).colspan(2);
//        window.row();
        window.add(fpsLabel).colspan(4);
        window.pack();

        stage.addActor(window);

        textfield.setTextFieldListener(new TextFieldListener() {
            public void keyTyped(TextField textField, char key) {
                if (key == '\n') textField.getOnscreenKeyboard().show(false);
            }
        });

        slider.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("UITest", "slider: " + slider.getValue());
            }
        });

        iconButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                new Dialog("Some Dialog", skin, "dialog") {
                    protected void result(Object object) {
                        System.out.println("Chosen: " + object);
                    }
                }.text("Are you enjoying this demo?").button("Yes", true).button("No", false).key(Keys.ENTER, true)
                        .key(Keys.ESCAPE, false).show(stage);
            }
        });

        checkBox.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.graphics.setContinuousRendering(checkBox.isChecked());
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fpsLabel.setText("fps: " + Gdx.graphics.getFramesPerSecond());

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        texture1.dispose();
        texture2.dispose();
    }
}