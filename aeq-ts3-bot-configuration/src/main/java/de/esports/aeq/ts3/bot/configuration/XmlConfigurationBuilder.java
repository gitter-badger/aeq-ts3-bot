/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.esports.aeq.ts3.bot.configuration;

import de.esports.aeq.ts3.bot.configuration.api.ConfigurationBuilder;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Used to build a {@link BotConfiguration} from an XML file.
 *
 * @author Lukas Kannenberg
 * @version 1.0
 * @since 01.09.2017
 */
public class XmlConfigurationBuilder implements ConfigurationBuilder {

    /**
     * The pathname of the XML configuration file.
     */
    private String pathname;

    /**
     * Constructs a new {@link XmlConfigurationBuilder}.
     *
     * @param pathname the pathname of the XML configuration file
     */
    public XmlConfigurationBuilder(String pathname) {
        this.pathname = pathname;
    }

    @Override
    public @NotNull BotConfiguration build() throws ConfigurationBuildException {
        File file = new File(pathname);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BotConfiguration.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (BotConfiguration) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new ConfigurationBuildException("unable to create configuration", e);
        }
    }

}
